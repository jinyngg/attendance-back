package com.toy4.domain.employee.dto.request;

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
public class ChangePasswordRequest {

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?!.*\\s).+$"
            , message = "비밀번호는 영어와 숫자를 혼용해야 하며 공백은 사용할 수 없습니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자 이상 최대 16글자 이하로 작성해야 합니다.")
    private String currentPassword;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?!.*\\s).+$"
            , message = "비밀번호는 영어와 숫자를 혼용해야 하며 공백은 사용할 수 없습니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자 이상 최대 16글자 이하로 작성해야 합니다.")
    private String password;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?!.*\\s).+$"
            , message = "비밀번호는 영어와 숫자를 혼용해야 하며 공백은 사용할 수 없습니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자 이상 최대 16글자 이하로 작성해야 합니다.")
    private String confirmPassword;

}
