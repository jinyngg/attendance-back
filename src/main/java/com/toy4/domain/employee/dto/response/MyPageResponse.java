package com.toy4.domain.employee.dto.response;

import static com.toy4.global.date.DateFormatter.*;
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
	private String hireDate;

	public static MyPageResponse from(Employee employee) {
		return MyPageResponse.builder()
			.name(employee.getName())
			.department(employee.getDepartment().getType().getDescription())
			.position(employee.getPosition().getType().getDescription())
			.employeeId(employee.getId())
			.hireDate(employee.getHireDate().format(formatter))
			.build();
	}
}
