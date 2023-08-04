package com.toy4.domain.employee.dto.request;

import com.toy4.domain.employee.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindPasswordRequest {

    private String email;

    public static EmployeeDto to(FindPasswordRequest request) {
        return EmployeeDto.builder()
                .email(request.getEmail())
                .build();
    }
}
