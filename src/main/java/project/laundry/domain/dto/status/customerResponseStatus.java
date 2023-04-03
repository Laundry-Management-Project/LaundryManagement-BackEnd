package project.laundry.domain.dto.status;

import lombok.Getter;
import lombok.Setter;
import project.laundry.domain.dto.reservationDto;

import java.util.List;

@Getter @Setter
public class customerResponseStatus extends responseStatus {

    List<reservationDto> reservations;

    public customerResponseStatus(String message, boolean status, String uid) {
        super(message, status, uid);
    }
}
