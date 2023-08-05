package com.toy4.domain.dutyHistory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.toy4.domain.dutyHistory.dto.DutyHistoryRequest;
import com.toy4.domain.dutyHistory.service.DutyHistoryService;
import com.toy4.global.response.dto.CommonResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/admin/duty")
@RequiredArgsConstructor
@RestController
public class DutyHistoryController {

	private final DutyHistoryService dutyHistoryService;

	@GetMapping
	public ResponseEntity<?> getDayOffs() {
		CommonResponse<?> response = dutyHistoryService.getDutyHistories();
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<?> updateStatusDuty(
		@Validated @RequestBody DutyHistoryRequest request) {
		CommonResponse<?> response = dutyHistoryService.updateStatusDuty(DutyHistoryRequest.to(request));
		return ResponseEntity.ok(response);
	}
}
