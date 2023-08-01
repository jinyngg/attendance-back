package com.toy4.domain.employee.dto.request;

import com.toy4.domain.employee.dto.EmployeeDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String email;
    private String password;

    public static EmployeeDto to(LoginRequest request) {
        return EmployeeDto.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
