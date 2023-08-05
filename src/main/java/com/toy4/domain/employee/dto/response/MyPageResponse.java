package com.toy4.domain.employee.dto.response;

import java.time.LocalDate;

import com.toy4.domain.employee.domain.Employee;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponse {

	private String name;
	private String department;
	private String position;
	private Long employeeId;
	private LocalDate hireDate;

	public static MyPageResponse from(Employee employee) {
		return MyPageResponse.builder()
			.name(employee.getName())
			.department(employee.getDepartment().getType().getDescription())
			.position(employee.getPosition().getType().getDescription())
			.employeeId(employee.getId())
			.hireDate(employee.getHireDate())
			.build();
	}
}
