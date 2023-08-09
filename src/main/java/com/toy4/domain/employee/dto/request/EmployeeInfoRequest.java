package com.toy4.domain.employee.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.employee.dto.response.EmployeeInfo;
import com.toy4.domain.position.type.PositionType;

import lombok.Getter;

@Getter
public class EmployeeInfoRequest {

	@NotNull
	private Long employeeId;
	@NotBlank
	private String departmentType;
	@NotBlank
	private String positionType;
	@NotBlank
	private String name;
	@NotBlank
	private String phone;
	@NotNull
	private LocalDate hireDate;

	public EmployeeInfo to() {
		return EmployeeInfo.builder()
			.employeeId(employeeId)
			.departmentType(DepartmentType.getByTypeString(departmentType))
			.positionType(PositionType.getByTypeString(positionType))
			.name(name)
			.phone(phone)
			.hireDate(hireDate)
			.build();
	}
}