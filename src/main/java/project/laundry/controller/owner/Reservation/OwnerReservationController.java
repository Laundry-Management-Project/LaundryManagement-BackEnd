package project.laundry.controller.owner.Reservation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.request.OwnerReservationForm;
import project.laundry.data.response.ReservationDtoList;
import project.laundry.data.response.common.ReservationDto;
import project.laundry.service.owner.ReservationList.ReservationListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnerReservationController {

    private final ReservationListService service;

    @ApiOperation(value = "세탁물 예약 목록")
    @GetMapping("owner/{buId}/reservations")
    public ResponseEntity<ReservationDtoList> ReservationList(
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "page", value = "현재 페이지의 숫자 (1 부터 시작)", required = true, example = "1") @RequestParam("page") Integer pageNumber,
            @ApiParam(name = "size", value = "현재 페이지에 보여줄 요소의 개수", required = true, example = "10") @RequestParam("size") Integer pageSize) {
        return service.findReservationsByBusiness_id(buId, pageNumber, pageSize);
    }

    @ApiOperation(value = "세탁물 수정")
    @PutMapping("owner/{buId}/reservations/{reId}/update")
    public ResponseEntity<ReservationDto> updateReservation(
            @ApiParam(name = "form", value = "하단 OwnerReservationForm 참조", required = true) @RequestBody OwnerReservationForm form,
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "reId", value = "예약 고유 id", required = true) @PathVariable("reId") String reId) {
        return service.updateReservation(form, buId, reId);
    }


}
