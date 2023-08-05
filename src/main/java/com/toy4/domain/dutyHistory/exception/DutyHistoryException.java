package com.toy4.domain.dutyHistory.exception;

import org.springframework.http.HttpStatus;

import com.toy4.global.response.type.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DutyHistoryException extends RuntimeException {

	private ErrorCode errorCode;
	private String errorMessage;
	private HttpStatus httpStatus;

	public DutyHistoryException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorCode.getMessage();
		this.httpStatus = errorCode.getHttpStatus();
	}

}
