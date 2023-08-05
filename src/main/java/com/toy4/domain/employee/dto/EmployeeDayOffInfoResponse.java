package com.toy4.domain.employee.dto;

import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.position.type.PositionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmployeeDayOffInfoResponse {
	private Long id;
	private String name;
	private DepartmentType department;
	private PositionType position;
	private String hireDate;
	private float dayOffRemains;
	private float dayOffUsed;
	private float dayOffTotal;
}