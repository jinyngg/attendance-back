package com.toy4.domain.employee.dto;

import com.toy4.domain.employee.dto.request.ValidateMatchPasswordRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateMatchPassword {

    private String password;

    public static ValidateMatchPassword fromRequest(ValidateMatchPasswordRequest request) {
        return ValidateMatchPassword.builder()
                .password(request.getPassword())
                .build();
    }
}
