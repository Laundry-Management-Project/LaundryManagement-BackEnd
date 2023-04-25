package project.laundry.data.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "회원가입 시 필요한 정보")
@Getter @Setter
public class signUpForm {

    @ApiModelProperty(value = "로그인 시 필요한 id", example = "test", required = true)
    @NotBlank
    private String id;

    @ApiModelProperty(value = "사용자(손님, 사장님)의 이름 또는 닉네임", example = "홍길동, TestName", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "로그인 시 필요한 password", example = "1234", required = true)
    @NotBlank
    private String password;

    @ApiModelProperty(value = "사용자(손님, 사장님)의 핸드폰 번호", example = "010-1234-5678", required = true)
    @NotBlank
    private String phone;
}
