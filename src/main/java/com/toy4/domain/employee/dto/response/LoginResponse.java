package com.toy4.domain.employee.dto.response;

import com.toy4.global.token.dto.TokenDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private Long id;
    private TokenDto token;
}
