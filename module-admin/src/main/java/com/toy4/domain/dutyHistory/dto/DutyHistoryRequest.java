package com.toy4.domain.dutyHistory.dto;
import com.toy4.domain.schedule.RequestStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class DutyHistoryRequest {

	private Long dutyId;
	private String status;

	public static DutyHistoryDto to(DutyHistoryRequest request) {
		return DutyHistoryDto.builder()
			.id(request.getDutyId())
			.status(RequestStatus.getByTypeString(request.getStatus()))
			.build();
	}
}
