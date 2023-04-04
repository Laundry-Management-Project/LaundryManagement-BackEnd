package project.laundry.data.dto.owner;

import project.laundry.data.dto.responseStatus;

public class ownerSignUpDto extends responseStatus {
    public ownerSignUpDto(String message, Boolean status, String uid) {
        super(message, status, uid);
    }
}
