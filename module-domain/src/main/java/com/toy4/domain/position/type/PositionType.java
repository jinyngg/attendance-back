package com.toy4.domain.position.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PositionType {

  STAFF("사원"),
  ASSISTANT_MANAGER("대리"),
  MANAGER("과장"),
  DEPUTY_GENERAL_MANAGER("차장"),
  GENERAL_MANAGER("부장"),
  ;

  private final String description;

}
