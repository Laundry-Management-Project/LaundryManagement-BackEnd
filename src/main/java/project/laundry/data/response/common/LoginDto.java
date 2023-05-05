package project.laundry.data.response.common;

import lombok.*;
import project.laundry.data.response.responseStatus;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto extends responseStatus {

    private String uid;

    private String token;

    public LoginDto(String message, boolean status) {
        super(message, status);
    }
}
