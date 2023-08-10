package com.toy4.domain.employee.dto;

import com.toy4.domain.employee.dto.request.ChangePasswordRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePassword {

    private String currentPassword;
    private String password;
    private String confirmPassword;

    public static ChangePassword fromRequest(ChangePasswordRequest request) {
        return ChangePassword.builder()
                .currentPassword(request.getCurrentPassword())
                .password(request.getPassword())
                .confirmPassword(request.getConfirmPassword())
                .build();
    }
}
