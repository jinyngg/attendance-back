package com.toy4.domain.dayOffHistory.repository.info;

import java.util.List;

import com.toy4.domain.employee.dto.EmployeeDayOffInfoResponse;

public interface DayOffHistoryCustomRepository {
	List<EmployeeDayOffInfoResponse> getEmployeeDayOff();
}
