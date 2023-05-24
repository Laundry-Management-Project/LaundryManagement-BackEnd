package project.laundry.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "로그인 폼")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class loginForm {

    @ApiModelProperty(value = "손님, 사장을 구분하기 위한 타입(대문자 허용 X)", example = "cu | ow", required = true)
    private String user_type;

    @ApiModelProperty(value = "로그인 시 필요한 id", example = "test", required = true)
    @NotBlank
    private String id;

    @ApiModelProperty(value = "로그인 시 필요한 비밀번호", example = "1234", required = true)
    @NotBlank
    private String password;

}
