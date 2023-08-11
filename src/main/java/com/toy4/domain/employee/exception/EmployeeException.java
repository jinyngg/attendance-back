package com.toy4.domain.employee.exception;

import com.toy4.global.response.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeException extends RuntimeException {

  private ErrorCode errorCode;
  private String errorMessage;
  private HttpStatus httpStatus;

  public EmployeeException(ErrorCode errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getMessage();
    this.httpStatus = errorCode.getHttpStatus();
  }

}
