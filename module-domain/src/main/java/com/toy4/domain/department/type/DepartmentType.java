package com.toy4.domain.department.type;

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

}
