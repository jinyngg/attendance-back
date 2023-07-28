package com.toy4.domain.employee.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeRole {

  ADMIN("ROLE_ADMIN", "어드민") 
    , USER("ROLE_USER", "유저")

      ;

  private final String role;
  private final String description;
}
