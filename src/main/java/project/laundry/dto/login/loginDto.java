package project.laundry.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class loginDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    // 손님 - "CU", 사장 - "OW"
    @NotBlank
    private String loginType;
}
