package project.laundry.controller.customer.Reservation;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.response.common.ReservationDto;
import project.laundry.data.request.reservationForm;
import project.laundry.service.customer.CustomerReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerReservationController {

    private final CustomerReservationService service;

    @ApiOperation(value = "세탁물 신청 목록")
    @GetMapping("customer/reservations")
    public ResponseEntity<List<ReservationDto>> Reservations(@RequestParam("uId") String uId) {
        return service.findReservations(uId);
    }

    @ApiOperation(value = "손님 예약(등록)")
    @PostMapping("customer/{buId}/reservation")
    public ResponseEntity<ReservationDto> ReservationAdd(
            @RequestBody @Validated reservationForm form,
            @PathVariable("buId") String buId,
            @RequestParam("uId") String uId) {

        return service.saveReservation(form, buId, uId);
    }
}
