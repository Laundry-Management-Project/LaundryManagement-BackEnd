package project.laundry.controller.owner.Calender;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.laundry.service.owner.Calender.OwnerCalenderService;

@RestController
@RequiredArgsConstructor
public class CalenderController {

    private final OwnerCalenderService service;


    @ApiOperation(value = "사업장 캘린더 세부 화면")
    @GetMapping("owner/business/calender")
    public void businessCalenderDetail(
            @ApiParam(name = "buId", value = "사업장 고유 uid", required = true) @RequestParam String buId,
            @ApiParam(name = "year", value = "년", example = "2023") @RequestParam String year,
            @ApiParam(name = "month", value = "월", example = "04") @RequestParam String month,
            @ApiParam(name = "day", value = "일", example = "17") @RequestParam String day) {

    }

}
