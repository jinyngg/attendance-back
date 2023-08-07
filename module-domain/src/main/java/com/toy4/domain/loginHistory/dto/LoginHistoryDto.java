package com.toy4.domain.loginHistory.dto;

import com.toy4.domain.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryDto {

    private Employee employee;
    private String clientIp;
    private String userAgent;
}
