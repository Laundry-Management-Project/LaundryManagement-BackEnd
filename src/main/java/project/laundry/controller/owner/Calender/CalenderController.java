package project.laundry.controller.owner.Calender;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.laundry.data.response.incomeDtoList;
import project.laundry.service.owner.Calender.OwnerCalenderService;

@RestController
@RequiredArgsConstructor
public class CalenderController {

    private final OwnerCalenderService service;


    @ApiOperation(value = "세탁소 캘린더 세부 화면")
    @GetMapping("owner/{buId}/calender")
    public ResponseEntity<incomeDtoList> businessCalenderDetail(
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "year", value = "년", example = "2023") @RequestParam String year,
            @ApiParam(name = "month", value = "월", example = "04") @RequestParam String month,
            @ApiParam(name = "day", value = "일", example = "17") @RequestParam String day) {

        return service.responseIncomeDto(buId, year, month, day);
    }

}
