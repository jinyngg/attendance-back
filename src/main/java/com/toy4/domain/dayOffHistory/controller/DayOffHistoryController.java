package com.toy4.domain.dayOffHistory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy4.domain.dayOffHistory.dto.DayOffHistoryRequest;
import com.toy4.domain.dayOffHistory.service.DayOffHistoryService;
import com.toy4.global.response.dto.CommonResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/admin/day-offs")
@RequiredArgsConstructor
@RestController
public class DayOffHistoryController {

	private final DayOffHistoryService dayOffHistoryService;

	@GetMapping
	public ResponseEntity<?> getDayOffs() {
		CommonResponse<?> response = dayOffHistoryService.getDayOffs();
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<?> updateStatusDayOff(
		@Validated @RequestBody DayOffHistoryRequest request) {
		CommonResponse<?> response = dayOffHistoryService.updateStatusDayOff(DayOffHistoryRequest.to(request));
		return ResponseEntity.ok(response);
	}


}
