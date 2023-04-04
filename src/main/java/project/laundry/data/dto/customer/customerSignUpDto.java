package project.laundry.data.dto.customer;

import project.laundry.data.dto.responseStatus;

public class customerSignUpDto extends responseStatus {

    public customerSignUpDto(String message, Boolean status, String uid) {
        super(message, status, uid);
    }
}
