package com.toy4.domain.dutyHistory.repository;

import com.toy4.domain.dutyHistory.domain.DutyHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DutyHistoryRepositoryTest {

    @Autowired
    private DutyHistoryRepository dutyHistoryRepository;

    @DisplayName("[실패] 당직 날짜와 겹침 존재")
    @Test
    void whenOverlappedDate_thenPresent() {
        final Long employeeId = 1L;
        LocalDate requestedDutyDate = LocalDate.of(2023, 8, 8);

        for (int i = 0; i < 2; ++i) {
            requestedDutyDate = requestedDutyDate.plusDays(1);
            Optional<DutyHistory> overlappedDate = dutyHistoryRepository.findOverlappedDate(employeeId, requestedDutyDate);
            assertThat(overlappedDate).isPresent();
        }
    }

    @DisplayName("[성공] 당직 날짜 겹침 없음")
    @Test
    void whenNotOverlappedDate_thenEmpty() {
        final Long employeeId = 1L;
        LocalDate requestedDutyDate = LocalDate.of(2023, 8, 10);

        for (int i = 0; i < 5; ++i) {
            requestedDutyDate = requestedDutyDate.plusDays(1);
            Optional<DutyHistory> overlappedDate = dutyHistoryRepository.findOverlappedDate(employeeId, requestedDutyDate);
            assertThat(overlappedDate).isEmpty();
        }
    }
}