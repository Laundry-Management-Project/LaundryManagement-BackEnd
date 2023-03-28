package project.laundry.dto.login;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Data
public class loginDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
