package com.toy4.domain.employee.service;

import com.toy4.domain.employee.dto.EmployeeDto;
import com.toy4.global.response.dto.CommonResponse;

public interface EmployeeService {

    // 이메일 중복 확인
    CommonResponse<?> validateUniqueEmail(String email);

    // 회원가입
    CommonResponse<?> signup(EmployeeDto request);

    // 로그인
    CommonResponse<?> login(EmployeeDto request);
}
