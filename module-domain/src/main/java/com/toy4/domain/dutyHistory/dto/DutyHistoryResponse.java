package com.toy4.domain.dutyHistory.dto;

import static com.toy4.global.date.DateFormatter.*;
import com.toy4.domain.dutyHistory.domain.DutyHistory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DutyHistoryResponse {
	private Long employeeId;
	private String name;
	private String department;
	private String position;
	private String hireDate;
	private String requestDate;
	private Long dutyId;
	private String date;
	private String status;

	public static DutyHistoryResponse from (DutyHistory dutyHistory) {

		return DutyHistoryResponse.builder()
			.employeeId(dutyHistory.getEmployee().getId())
			.name(dutyHistory.getEmployee().getName())
			.department(dutyHistory.getEmployee().getDepartment().getType().getDescription())
			.position(dutyHistory.getEmployee().getPosition().getType().getDescription())
			.hireDate(dutyHistory.getDate().format(formatter))
			.requestDate(dutyHistory.getDate().format(formatter))
			.dutyId(dutyHistory.getId())
			.date(dutyHistory.getDate().format(formatter))
			.status(dutyHistory.getStatus().getDescription())
			.build();
	}
}
