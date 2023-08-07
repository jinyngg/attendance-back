package com.toy4.domain.employee.dto;

import com.toy4.domain.department.domain.Department;
import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.employee.type.EmployeeRole;
import com.toy4.domain.position.domain.Position;
import com.toy4.domain.position.type.PositionType;
import com.toy4.domain.status.domain.Status;
import com.toy4.domain.status.type.StatusType;
import com.toy4.global.toekn.dto.TokenDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeDto {

    private Long id;
    private String uuid;

    private Position position;
    private PositionType positionType;

    private Department department;
    private DepartmentType departmentType;

    private Status status;
    private StatusType statusType;

    private String name;
    private String email;
    private String phone;

    private String password;
    private String confirmPassword;

    private LocalDate hireDate;
    private LocalDate quitDate;
    private LocalDate birthdate;

    private int dayOffRemains;

    private EmployeeRole role;

    private String zipAddress;
    private String roadAddress;
    private String detailAddress;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    private TokenDto tokenDto;

    public void addDepartment(Department department) {
        this.department = department;
    }

}
