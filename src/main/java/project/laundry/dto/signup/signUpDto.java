package project.laundry.dto.signup;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class signUpDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    // 손님 - "CU", 사장 - "OW"
    private String userType;
}
