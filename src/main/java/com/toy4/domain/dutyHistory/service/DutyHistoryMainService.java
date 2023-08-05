package com.toy4.domain.dutyHistory.service;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import com.toy4.domain.dayOffHistory.repository.DayOffHistoryRepository;
import com.toy4.domain.dutyHistory.domain.DutyHistory;
import com.toy4.domain.dutyHistory.dto.DutyRegistrationRequest;
import com.toy4.domain.dutyHistory.exception.DutyHistoryException;
import com.toy4.domain.dutyHistory.repository.DutyHistoryRepository;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.global.response.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DutyHistoryMainService {

    private final DutyHistoryRepository dutyHistoryRepository;
    private final EmployeeRepository employeeRepository;
    private final DayOffHistoryRepository dayOffHistoryRepository;

    public void registerDuty(DutyRegistrationRequest requestBody) {
        Employee employee = employeeRepository.findById(requestBody.getEmployeeId())
                .orElseThrow(() -> new DutyHistoryException(ErrorCode.EMPLOYEE_NOT_FOUND));

        Optional<DutyHistory> overlappedDutyHistory =
                dutyHistoryRepository.findOverlappedDate(requestBody.getEmployeeId(), requestBody.getDate());
        if (overlappedDutyHistory.isPresent())
            throw new DutyHistoryException(ErrorCode.OVERLAPPED_DUTY_DATE);
        Optional<DayOffHistory> overlappedDayOffHistory =
                dayOffHistoryRepository.findOverlappedDate(requestBody.getEmployeeId(), requestBody.getDate());
        if (overlappedDayOffHistory.isPresent())
            throw new DutyHistoryException(ErrorCode.OVERLAPPED_DUTY_DATE);

        dutyHistoryRepository.save(DutyHistory.from(employee, requestBody));
    }
}
