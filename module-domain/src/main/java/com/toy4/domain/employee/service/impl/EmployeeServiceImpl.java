package com.toy4.domain.employee.service.impl;

import static com.toy4.global.response.type.ErrorCode.ALREADY_EXISTS_EMAIL;
import static com.toy4.global.response.type.SuccessCode.AVAILABLE_EMAIL;

import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.domain.employee.service.EmployeeService;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final ResponseService responseService;

  @Override
  public CommonResponse<?> validateEmail(String email) {
    // 1. 유효성 검사(이메일 중복 검사)
    duplicatedCheckEmail(email);
    return responseService.successWithNoContent(AVAILABLE_EMAIL);
  }

  /** 이메일 중복 확인 */
  private void duplicatedCheckEmail(String email) {
    // 1. 이메일 중복 검사
    if (!employeeRepository.existsByEmail(email)) {
      throw new EmployeeException(ALREADY_EXISTS_EMAIL);
    }
  }
}
