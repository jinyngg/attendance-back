package com.toy4.domain.employee.dto;

import com.toy4.domain.employee.type.EmployeeRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeLogin {

    private Long id;
    private EmployeeRole role;

}
