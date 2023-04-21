package project.laundry.controller.owner.Reservation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.response.common.ReservationDto;
import project.laundry.service.owner.ReservationList.ReservationListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnerReservationController {

    private final ReservationListService service;

    @ApiOperation(value = "세탁물 예약 목록")
    @GetMapping("owner/{buId}/reservations")
    public ResponseEntity<List<ReservationDto>> ReservationList(@ApiParam(name = "buId", value = "사업장 고유 uid", required = true) @PathVariable("buId") String buId) {
        return service.findReservationsByBusiness_id(buId);
    }

    @PutMapping("owner/{buId}/reservations/{reId}/update")
    public ResponseEntity<ReservationDto> updateReservation(
            @RequestBody ReservationDto form,
            @PathVariable("buId") String buId,
            @PathVariable("reId") String reId) {
        return service.updateReservation(form, buId, reId);
    }


}
