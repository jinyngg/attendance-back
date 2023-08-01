package com.toy4.employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy4.domain.employee.service.EmployeeService;
import com.toy4.global.response.dto.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Log4j2
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping(path="/my-page/{id}")
	public ResponseEntity<?> getMyPage(@PathVariable Long id) {
		CommonResponse<?> response = employeeService.getMyPage(id);
		return ResponseEntity.ok(response);
	}
}
