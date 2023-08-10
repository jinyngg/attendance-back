package com.toy4.domain.employee.service;

import com.toy4.domain.employee.dto.response.EmployeeDto;
import com.toy4.domain.employee.dto.response.EmployeeInfo;
import com.toy4.domain.employee.dto.response.PersonalInfo;
import com.toy4.global.response.dto.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    // 이메일 중복 확인
    CommonResponse<?> validateUniqueEmail(String email);

    // 회원가입
    CommonResponse<?> signup(EmployeeDto request);
//    CommonResponse<?> signup(EmployeeDto request, MultipartFile profileImage);

    // 로그인
//    CommonResponse<?> login(EmployeeDto request);

    // 비밀번호 변경 이메일 전송
    CommonResponse<?> sendPasswordChangeEmail(String email);

    // 비밀번호 변경
    CommonResponse<?> changePassword(EmployeeDto request, String uuid);

    void updateEmployeeInfo(EmployeeInfo dto, MultipartFile profileImageFile);
    CommonResponse<?> getEmployeeInfo(Long id);
    CommonResponse<?> getMyPage(Long id);
    void updatePersonalInfo(PersonalInfo dto, MultipartFile profileImageFile);
    CommonResponse<?> getEmployeeDayOffInfo();

}
