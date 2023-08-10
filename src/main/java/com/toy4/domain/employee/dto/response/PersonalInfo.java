package com.toy4.domain.employee.dto.response;

import com.toy4.domain.department.type.DepartmentType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class PersonalInfo {
	private Long employeeId;
	private DepartmentType departmentType;
	private String name;
	private String phone;
}
