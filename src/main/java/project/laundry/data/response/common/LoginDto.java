package project.laundry.data.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import project.laundry.data.response.responseStatus;

@ApiModel(value = "로그인 응답 데이터")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto extends responseStatus {

    @ApiModelProperty(value = "고유 id", example = "41f8eb6d-c99d-46af-87f1-7d9a6624753c", required = true)
    private String uid;

    @ApiModelProperty(value = "서버에서 발급한 JWT 토큰", example = "헤더.페이로드.서명 으로 구분된 JWT 토큰", required = true)
    private String token;

    public LoginDto(String message, boolean status) {
        super(message, status);
    }
}
