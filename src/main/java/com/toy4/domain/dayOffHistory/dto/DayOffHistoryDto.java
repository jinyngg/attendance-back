package com.toy4.domain.dayOffHistory.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.toy4.domain.dayoff.domain.DayOff;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.schedule.RequestStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DayOffHistoryDto {
	private Long id;
	private Employee employee;
	private DayOff dayOff;
	private RequestStatus status;
	private Date startDate;
	private Date endDate;
	private float totalAmount;
	private String reason;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
