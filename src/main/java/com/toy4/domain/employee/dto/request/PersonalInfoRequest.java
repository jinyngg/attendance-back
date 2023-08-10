package com.toy4.domain.employee.dto.request;

import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.employee.dto.response.PersonalInfo;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class PersonalInfoRequest {

	@NotNull
	private Long employeeId;
	@NotBlank
	private String department;
	@NotBlank
	private String name;
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$"
		, message = "전화번호 형식이 맞지 않습니다.(01X-XXX(X)-XXXX)")
	private String phone;

	public PersonalInfo to() {
		return PersonalInfo.builder()
			.employeeId(employeeId)
			.departmentType(DepartmentType.getByDescription(department))
			.name(name)
			.phone(phone)
			.build();
	}
}