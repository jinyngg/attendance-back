package com.toy4.domain.employee.dto.response;

import com.toy4.domain.employee.dto.EmployeeLogin;
import com.toy4.global.token.dto.TokenDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private EmployeeLogin employee;
    private TokenDto token;
}
