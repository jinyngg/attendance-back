package com.toy4.domain.status.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {

  JOINED("입사"),
  RESIGNED("퇴사"),
  REJOINED("재입사"),
  ON_LEAVE("휴직"),
  REINSTATED("복직"),
  RETIRED("퇴직")
  ;

  private final String description;

}
