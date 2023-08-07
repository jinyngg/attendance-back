package com.toy4.domain.dayOffHistory.service;

import com.toy4.domain.dayOffHistory.dto.DayOffHistoryDto;
import com.toy4.global.response.dto.CommonResponse;

public interface DayOffHistoryService {
	CommonResponse<?> getEmployeeApprovedDayOff(Long EmployeeId);
	CommonResponse<?> getDayOffs();
	CommonResponse<?> updateStatusDayOff(DayOffHistoryDto dto);
}
