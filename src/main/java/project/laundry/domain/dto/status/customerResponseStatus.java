package project.laundry.domain.dto.status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class customerResponseStatus extends responseStatus {

    public customerResponseStatus(String message, boolean status, String uid) {
        super(message, status, uid);
    }
}
