package project.laundry.data.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "로그인 시 필요한 정보")
@Getter @Setter
public class loginForm {

    @ApiModelProperty(value = "로그인 시 필요한 id", example = "test", required = true)
    @NotBlank
    private String id;

    @ApiModelProperty(value = "로그인 시 필요한 비밀번호", example = "1234", required = true)
    @NotBlank
    private String password;

}
