package com.toy4.domain.employee.service;

import org.springframework.web.multipart.MultipartFile;

import com.toy4.domain.employee.dto.EmployeeDto;
import com.toy4.global.response.dto.CommonResponse;

public interface EmployeeService {

    // 이메일 유효성 확인
    CommonResponse<?> validateEmail(String email);
    CommonResponse<?> updateEmployeeInfo(EmployeeDto employeeDto, MultipartFile file);
    CommonResponse<?> getEmployeeInfo(Long id);
    CommonResponse<?> getMyPage(Long id);

}
