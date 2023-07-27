package com.toy4.domain.response.service;


import com.toy4.domain.response.dto.CommonResponse;
import com.toy4.domain.response.type.ErrorCode;
import com.toy4.domain.response.type.SuccessCode;
import java.util.List;

public interface ResponseService {

    // 단일 데이터가 존재하는 성공 응답
    <T> CommonResponse<T> success(T data, SuccessCode successCode);

    // 복수 데이터가 존재하는 성공 응답
    <T> CommonResponse<List<T>> successList(List<T> data, SuccessCode successCode);

    // 데이터가 존재하지 않는 성공 응답
    CommonResponse<?> successWithNoContent(SuccessCode successCode);

    // 실패 응답
    <T> CommonResponse<T> failure(ErrorCode errorCode);
}
