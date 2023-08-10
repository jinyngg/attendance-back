package com.toy4.domain.employee.dto;

import com.toy4.domain.employee.dto.request.ResetPasswordRequest;
import com.toy4.domain.employee.dto.request.SignupRequest;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
public class ResetPassword {

    private String password;
    private String confirmPassword;
    private String authToken;

    public static ResetPassword fromRequest(ResetPasswordRequest request) {
        return ResetPassword.builder()
                .password(request.getPassword())
                .confirmPassword(request.getConfirmPassword())
                .authToken(request.getAuthToken())
                .build();
    }
}
