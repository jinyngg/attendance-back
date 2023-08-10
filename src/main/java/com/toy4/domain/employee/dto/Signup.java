package com.toy4.domain.employee.dto;

import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.employee.dto.request.SignupRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Signup {

    private String email;
    private String phone;
    private String name;
    private String password;
    private String confirmPassword;
    private LocalDate hireDate;
    private DepartmentType departmentType;

    public static Signup fromRequest(SignupRequest request) {
        return Signup.builder()
                .email(request.getEmail())
                .phone(request.getPhone())
                .name(request.getName())
                .password(request.getPassword())
                .confirmPassword(request.getConfirmPassword())
                .hireDate(request.getHireDate())
                .departmentType(request.getDepartment())
                .build();
    }
}
