package com.toy4.domain.department.type;

import java.util.Arrays;

import com.toy4.domain.dayoff.exception.DayOffException;
import com.toy4.global.response.type.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepartmentType {

  DEVELOPMENT("개발팀"),
  PLANNING("기획팀"),
  SALES("영업팀"),
  HR("인사팀"),
  ACCOUNTING("회계팀"),
  LEGAL("법무팀")
  ;

  private final String description;

  public static DepartmentType getByTypeString(String type) {
    return Arrays.stream(values())
        .filter(departmentType -> departmentType.description.equals(type))
        .findFirst()
        .orElseThrow(() -> new DayOffException(ErrorCode.INVALID_DAY_OFF_TYPE));
  }
}
