package com.toy4.domain.employee.dto.response;

import java.time.LocalDate;
import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.position.type.PositionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class EmployeeInfo {

	private Long employeeId;
	private DepartmentType departmentType;
	private PositionType positionType;
	private String name;
	private String phone;
	private LocalDate hireDate;
}
