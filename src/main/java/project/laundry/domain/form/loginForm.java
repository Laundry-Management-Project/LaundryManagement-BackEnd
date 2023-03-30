package project.laundry.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class loginForm {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

}
