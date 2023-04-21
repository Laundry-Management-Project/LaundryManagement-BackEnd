package project.laundry.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.laundry.data.response.common.ReservationDto;

import java.util.List;

@Getter @Setter
@Builder
public class ReservationDtoList {

    private List<ReservationDto> reservations;
}
