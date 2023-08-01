package com.toy4.domain.employee.controller;

import static com.toy4.global.response.type.ErrorCode.getByErrorCodeName;

import com.toy4.domain.employee.dto.request.EmailDuplicateCheckRequest;
import com.toy4.domain.employee.dto.request.LoginRequest;
import com.toy4.domain.employee.dto.request.SignupRequest;
import com.toy4.domain.employee.service.EmployeeService;
import com.toy4.global.aop.EmployeeLock;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.ErrorCode;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ResponseService responseService;

    @PostMapping("/validateEmail")
    public ResponseEntity<?> validateEmail(
            @Valid @RequestBody EmailDuplicateCheckRequest request
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = getErrorMessageFromBindingResult(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseService.failure(errorMessage));
        }

        CommonResponse<?> response = employeeService.validateUniqueEmail(request.getEmail());
        return ResponseEntity.ok(response);
    }

    @EmployeeLock
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Valid @RequestBody SignupRequest request
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = getErrorMessageFromBindingResult(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseService.failure(errorMessage));
        }

        CommonResponse<?> response = employeeService.signup(SignupRequest.to(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {
        CommonResponse<?> response = employeeService.login(LoginRequest.to(request));
        if (!response.isSuccess()) {
            String code = response.getCode();
            ResponseEntity.status(getHttpStatusByErrorCode(code)).body(response);
        }

        return ResponseEntity.ok(response);
    }

    /** bindingResult ErrorMessage 반환 */
    private String getErrorMessageFromBindingResult(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.get(0).getDefaultMessage();
    }

    /** errorCode HttpStatus 반환 */
    private HttpStatus getHttpStatusByErrorCode(String code) {
        ErrorCode errorCode = getByErrorCodeName(code);
        return errorCode.getHttpStatus();
    }

}
