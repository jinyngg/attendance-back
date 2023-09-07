package com.toy4.domain.dayOffHistory.service;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import com.toy4.domain.dayOffHistory.dto.DayOffRegistration;
import com.toy4.domain.dayOffHistory.exception.DayOffHistoryException;
import com.toy4.domain.dayOffHistory.repository.DayOffHistoryRepository;
import com.toy4.domain.dayoff.domain.DayOff;
import com.toy4.domain.dayoff.repository.DayOffRepository;
import com.toy4.domain.dayoff.type.DayOffType;
import com.toy4.domain.dutyHistory.repository.DutyHistoryRepository;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.global.response.type.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DayOffHistoryMainService.class))
class DayOffHistoryMainServiceTest {

    @Autowired
    private DayOffHistoryMainService sut;

    private DayOffHistoryMainService mockSut;
    @Mock
    private DayOffHistoryRepository mockDayOffHistoryRepo;
    @Mock
    private EmployeeRepository mockEmpRepo;
    @Mock
    private DayOffRepository mockDayOffRepo;
    @Mock
    private DutyHistoryRepository mockDutyHistoryRepo;

    @Mock
    private DayOffRegistration mockDto;

    @BeforeEach
    void setup() {
        mockSut = new DayOffHistoryMainService(mockDayOffHistoryRepo, mockEmpRepo, mockDayOffRepo, mockDutyHistoryRepo);
    }

    @DisplayName("[예외] 종료 날짜가 시작 날짜보다 빠른 경우")
    @Test
    void whenNegativeDaysDifference_thenThrowDayOffExceptionWith_INVERTED_DAY_OFF_RANGE() {
        when(mockDto.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        when(mockDto.getEndDate()).thenReturn(LocalDate.now().plusDays(1));

        Arrays.stream(DayOffType.values())
                .forEach(type -> assertThrowWithErrorCode(type, ErrorCode.INVERTED_DAY_OFF_RANGE));
    }

    @DisplayName("[예외] 시작과 끝 날짜가 다른 반차")
    @Test
    void whenHalfDayOffWithRangedStartEnd_thenThrowDayOffExceptionWith_RANGED_HALF_DAY_OFF() {
        when(mockDto.getStartDate()).thenReturn(LocalDate.now().plusDays(1));
        when(mockDto.getEndDate()).thenReturn(LocalDate.now().plusDays(2));

        Arrays.stream(DayOffType.values())
                .filter(DayOffType::isHalfDayOff)
                .forEach(type -> assertThrowWithErrorCode(type, ErrorCode.RANGED_HALF_DAY_OFF));
    }

    @DisplayName("[예외] 직원이 없는 경우")
    @Test
    void whenNotFoundEmployee_thenThrowDayOffExceptionWith_EMPLOYEE_NOT_FOUND() {
        when(mockDto.getStartDate()).thenReturn(LocalDate.now());
        when(mockDto.getEndDate()).thenReturn(LocalDate.now());
        when(mockDto.getEmployeeId()).thenReturn(10000L);

        Arrays.stream(DayOffType.values())
                .forEach(type -> assertThrowWithErrorCode(type, ErrorCode.EMPLOYEE_NOT_FOUND));
    }

    @DisplayName("[예외] 잔여 연차수 보다 연차 요청이 큰 경우")
    @Test
    void whenNotFoundEmployee_thenThrowDayOffExceptionWith_DAY_OFF_REMAINS_OVER() {
        when(mockDto.getStartDate()).thenReturn(LocalDate.now());
        when(mockDto.getEndDate()).thenReturn(LocalDate.now().plusDays(1000L));
        when(mockDto.getEmployeeId()).thenReturn(1L);

        Arrays.stream(DayOffType.values())
                .filter(type -> type == DayOffType.NORMAL_DAY_OFF)
                .forEach(type -> assertThrowWithErrorCode(type, ErrorCode.DAY_OFF_REMAINS_OVER));
    }

    private void assertThrowWithErrorCode(DayOffType type, ErrorCode errorCode) {
        when(mockDto.getType()).thenReturn(type);

        assertThatThrownBy(() -> sut.registerDayOff(mockDto))
                .isInstanceOf(DayOffHistoryException.class)
                .hasFieldOrPropertyWithValue("errorCode", errorCode);
    }

    @DisplayName("[성공] 시작과 끝이 같은 반차")
    @Test
    void whenHalfDayOffWithSameStartAndEnd_thenPass() {
        assertPassWithSameStartEnd(0.5f, DayOffType::isHalfDayOff);
    }

    @DisplayName("[성공] 시작과 끝이 같은 not 반차")
    @Test
    void whenSameStartAndEndDateWithNotHalfDayOff_thenPass() {
        assertPassWithSameStartEnd(1.0f, type -> !type.isHalfDayOff());
    }

    private void assertPassWithSameStartEnd(Float dayOffRemains, Predicate<DayOffType> dayOffTypePredicate) {
        LocalDate date = LocalDate.now();
        assertPass(date, date, dayOffRemains, dayOffTypePredicate);
    }

    @DisplayName("[성공] 시작과 끝이 다른 not 반차")
    @Test
    void whenLateEndDateWithNotDayOff_thenPass() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(2);
        assertPass(start, end, 3.0f, type -> !type.isHalfDayOff());
    }

    private void assertPass(LocalDate start, LocalDate end, Float dayOffRemains, Predicate<DayOffType> dayOffTypePredicate) {
        when(mockDto.getEmployeeId()).thenReturn(1L);
        when(mockDto.getStartDate()).thenReturn(LocalDate.of(start.getYear(), start.getMonthValue(), start.getDayOfMonth()));
        when(mockDto.getEndDate()).thenReturn(LocalDate.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth()));
        when(mockDto.getReason()).thenReturn("사유.");

        Employee mockEmp = mock(Employee.class);
        when(mockEmp.getDayOffRemains()).thenReturn(dayOffRemains);
        when(mockEmpRepo.findById(anyLong())).thenReturn(Optional.of(mockEmp));

        DayOff mockDayOff = mock(DayOff.class);
        when(mockDayOffRepo.findByType(any(DayOffType.class))).thenReturn(mockDayOff);

        Arrays.stream(DayOffType.values())
                .filter(dayOffTypePredicate)
                .forEach(type -> {
                    when(mockDto.getType()).thenReturn(type);

                    mockSut.registerDayOff(mockDto);
                });

        verify(mockDayOffHistoryRepo, times(2)).save(any(DayOffHistory.class));
    }

    @DisplayName("[예외] 시작일이 오늘 이전이면 신청 불가")
    @Test
    void whenPastDate_thenThrowsException() {
        // given
        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 10; ++i) {
            startDate = startDate.minusDays(1);
            when(mockDto.getStartDate()).thenReturn(startDate);

            // when, then
            assertThrowsWhenPastDate();
        }
    }

    private void assertThrowsWhenPastDate() {
        assertThatThrownBy(() -> sut.registerDayOff(mockDto))
                .isInstanceOf(DayOffHistoryException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PAST_DATE);
    }

    @DisplayName("[예외] 시작일이 오늘 이후면 지난 날짜 검증 통과")
    @Test
    void whenToday_thenThrowsException() {
        // given
        for (DayOffType type : DayOffType.values()) {
            when(mockDto.getType()).thenReturn(type);
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.minusDays(1);
            for (int i = 0; i < 10; ++i) {
                when(mockDto.getStartDate()).thenReturn(startDate);
                when(mockDto.getEndDate()).thenReturn(endDate);

                // when, then
                assertThrowsWhenTodayOrLate();

                endDate = startDate;
                startDate = startDate.plusDays(1);
            }
        }
    }

    void assertThrowsWhenTodayOrLate() {
        assertThatThrownBy(() -> sut.registerDayOff(mockDto))
                .isInstanceOf(DayOffHistoryException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVERTED_DAY_OFF_RANGE);
    }
}