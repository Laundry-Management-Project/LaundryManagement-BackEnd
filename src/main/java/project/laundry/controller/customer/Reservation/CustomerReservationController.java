package project.laundry.controller.customer.Reservation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.response.ReservationDtoList;
import project.laundry.data.response.common.ReservationDto;
import project.laundry.data.request.CustomerReservationForm;
import project.laundry.service.customer.CustomerReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerReservationController {

    private final CustomerReservationService service;

    @ApiOperation(value = "세탁물 신청 목록")
    @GetMapping("customer/reservations")
    public ResponseEntity<ReservationDtoList> Reservations(
            @ApiParam(name = "uId", value = "손님 고유 id", required = true) @RequestParam("uId") String uId,
            @ApiParam(name = "page", value = "현재 페이지의 숫자 (1 부터 시작)", required = true, example = "1") @RequestParam("page") Integer pageNumber,
            @ApiParam(name = "size", value = "현재 페이지에 보여줄 요소의 개수", required = true, example = "10") @RequestParam("size") Integer pageSize) {
        return service.findReservations(uId, pageNumber, pageSize);
    }

    @ApiOperation(value = "예약(등록)")
    @PostMapping("customer/{buId}/reservation")
    public ResponseEntity<ReservationDto> addReservation(
            @ApiParam(name = "form", value = "하단 CustomerReservationForm 참조", required = true) @RequestBody CustomerReservationForm form,
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "uId", value = "손님 고유 id", required = true) @RequestParam("uId") String uId) {

        return service.saveReservation(form, buId, uId);
    }

    @ApiOperation(value = "예약 업데이트")
    @PutMapping("customer/{reId}/update")
    public void updateReservation(
            @ApiParam(name = "form", value = "하단 CustomerReservationForm 참조", required = true) @RequestBody CustomerReservationForm form,
            @ApiParam(name = "reId", value = "예약 고유 id", required = true) @PathVariable("reId") String reId) {

        service.updateReservation(form, reId);
    }

    @ApiOperation(value = "예약 삭제")
    @DeleteMapping("customer/{reId}/delete")
    public void deleteReservation(
            @ApiParam(name = "reId", value = "예약 고유 id", required = true) @PathVariable("reId") String reId,
            @ApiParam(name = "uId", value = "손님 고유 id", required = true) @RequestParam("uId") String uId) {
        service.deleteReservation(reId, uId);
    }

}
