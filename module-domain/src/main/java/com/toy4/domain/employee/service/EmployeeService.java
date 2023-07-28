package com.toy4.domain.employee.service;

import com.toy4.global.response.dto.CommonResponse;

public interface EmployeeService {

    // 이메일 유효성 확인
    CommonResponse<?> validateEmail(String email);
}
