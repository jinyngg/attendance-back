package com.toy4.domain.department.type;

import java.util.Arrays;
import com.toy4.domain.department.exception.DepartmentException;
import com.toy4.global.response.type.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepartmentType {
  AUTOMOBILE("순양자동차"),
  STORE("순양백화점"),
  SECURITIES("순양증권"),
  SEMICON("순양반도체"),
  MEDICAL("순양의료원"),
  MICRO("순양마이크로"),
  UNKNOWN("부서없음")
  ;

  private final String description;

  public static DepartmentType getByTypeString(String type) {
    return Arrays.stream(values())
        .filter(departmentType -> departmentType.getDescription().equals(type))
        .findFirst()
        .orElseThrow(() -> new DepartmentException(ErrorCode.INVALID_REQUEST_DEPARTMENT_TYPE));
  }
}
