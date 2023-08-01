package com.toy4.domain.employee.service.impl;

import static com.toy4.global.response.type.ErrorCode.*;
import static com.toy4.global.response.type.SuccessCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.dto.EmployeeDto;
import com.toy4.domain.employee.dto.MyPageResponse;
import com.toy4.domain.employee.dto.PersonalInfoResponse;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.domain.employee.service.EmployeeService;
import com.toy4.global.file.EmployeeProfileImageService;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ResponseService responseService;
	private final EmployeeProfileImageService employeeProfileImageService;

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

	@Override
	@Transactional
	public CommonResponse<?> updateEmployeeInfo(EmployeeDto employeeDto, MultipartFile profileImageFile) {
		Employee employee = employeeRepository.findById(employeeDto.getId())
			.orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));

		String profileImagePath = employeeProfileImageService.getDefaultFile();

		if (!profileImageFile.isEmpty()) {
			profileImagePath = employeeProfileImageService.saveFile(employeeDto.getId(), profileImageFile);
        }

		employeeProfileImageService.removeIfFileExists(employee.getProfilePath());

		employee.update(employeeDto, profileImagePath);

		return responseService.success(employee.getId(), COMPLETE_PERSONAL_INFO_UPDATE);
	}

	@Override
	@Transactional(readOnly = true)
	public CommonResponse<?> getEmployeeInfo(Long id) {
		Employee employee = employeeRepository.findById(id)
			.orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));
		PersonalInfoResponse response = PersonalInfoResponse.from(employee);
		return responseService.success(response, SUCCESS);
	}

	@Override
	public CommonResponse<?> getMyPage(Long id) {
		Employee employee = employeeRepository.findById(id)
			.orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));
		MyPageResponse response = MyPageResponse.from(employee);
		return responseService.success(response, SUCCESS);
	}
}
