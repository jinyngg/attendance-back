package com.toy4.domain.loginHistory.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.loginHistory.dto.LoginHistoryDto;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LoginHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    private String clientIp;
    private String userAgent;

    public static LoginHistory fromDto(LoginHistoryDto loginHistoryDto) {
        return LoginHistory.builder()
                .employee(loginHistoryDto.getEmployee())
                .clientIp(loginHistoryDto.getClientIp())
                .userAgent(loginHistoryDto.getUserAgent())
                .build();
    }

}
