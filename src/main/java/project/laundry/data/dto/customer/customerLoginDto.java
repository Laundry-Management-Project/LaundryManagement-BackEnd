package project.laundry.data.dto.customer;

import lombok.Getter;
import lombok.Setter;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.data.dto.responseStatus;

import java.util.List;

@Getter @Setter
public class customerLoginDto extends responseStatus {

    List<reservationDto> reservations;

    public customerLoginDto(String message, boolean status, String uid) {
        super(message, status, uid);
    }
}
