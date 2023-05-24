package project.laundry.controller.customer.Business;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.response.BusinessDtoList;
import project.laundry.data.response.common.BusinessDto;
import project.laundry.service.customer.CustomerBusinessService;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerBusinessController {

    private final CustomerBusinessService customerService;

    @ApiOperation(value = "세탁소 전체 목록 조회")
    @GetMapping("/businesses")
    public ResponseEntity<BusinessDtoList> Businesses(
            @ApiParam(name = "page", value = "현재 페이지의 숫자 (1 부터 시작)", required = true, example = "1") @RequestParam("page") Integer pageNumber,
            @ApiParam(name = "size", value = "현재 페이지에 보여줄 요소의 개수", required = true, example = "10") @RequestParam("size") Integer pageSize) {
        return customerService.findBusinesses(pageNumber, pageSize);
    }

    @ApiOperation(value = "세탁소 상세 화면(조회만 가능)")
    @GetMapping("/{buId}")
    public ResponseEntity<BusinessDto> ReservationBusinessDetail(
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId) {
        return customerService.findBusiness(buId);
    }


}
