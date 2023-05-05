package project.laundry.data.response.common;

import lombok.*;
import project.laundry.data.response.responseStatus;

@Getter @Setter
public class SignupDto extends responseStatus {

    private String uid;

    public SignupDto(String message, Boolean status) {
        super(message, status);
    }
}
