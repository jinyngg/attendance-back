package com.toy4.domain.dutyHistory.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class DutyRegistrationRequest {

    @NotNull
    private Long employeeId;
    @NotNull
    private LocalDate date;
    @NotNull
    private String reason;
}
