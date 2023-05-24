package project.laundry.data.response.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import project.laundry.data.response.responseStatus;

@Getter @Setter
public class SignupDto extends responseStatus {

    @ApiModelProperty(value = "고유 id", example = "41f8eb6d-c99d-46af-87f1-7d9a6624753c", required = true)
    private String uid;

    public SignupDto(String message, Boolean status) {
        super(message, status);
    }
}
