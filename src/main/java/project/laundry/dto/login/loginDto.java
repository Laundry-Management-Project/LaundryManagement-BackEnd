package project.laundry.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    private String userType;
}
