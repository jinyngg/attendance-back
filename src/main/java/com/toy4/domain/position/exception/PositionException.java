package com.toy4.domain.position.exception;

import com.toy4.global.response.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PositionException extends RuntimeException {

  private ErrorCode errorCode;
  private String errorMessage;
  private HttpStatus httpStatus;

  public PositionException(ErrorCode errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getMessage();
    this.httpStatus = errorCode.getHttpStatus();
  }

}
