package com.toy4.domain.dutyHistory.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.schedule.RequestStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DutyHistoryDto {

	private Long id;
	private Employee employee;
	private RequestStatus status;
	private LocalDate date;
	private String reason;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
