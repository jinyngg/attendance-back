package com.toy4.domain.employee.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toy4.domain.department.type.DepartmentType;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "이메일이 누락되었습니다.")
    @Email(message = "이메일 주소 형식이 잘못되었습니다.")
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$"
            , message = "전화번호 형식이 맞지 않습니다.(01X-XXX(X)-XXXX)")
    private String phone;

    @NotBlank(message = "이름이 누락되었습니다.")
    private String name;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?!.*\\s).+$"
            , message = "비밀번호는 영어와 숫자를 혼용해야 하며 공백은 사용할 수 없습니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자 이상 최대 16글자 이하로 작성해야 합니다.")
    private String password;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?!.*\\s).+$"
            , message = "비밀번호는 영어와 숫자를 혼용해야 하며 공백은 사용할 수 없습니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자 이상 최대 16글자 이하로 작성해야 합니다.")
    private String confirmPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate hireDate;

    private DepartmentType department;

}
