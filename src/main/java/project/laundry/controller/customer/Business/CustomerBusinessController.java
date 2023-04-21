package project.laundry.controller.customer.Business;

import io.swagger.annotations.ApiOperation;
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


    @GetMapping("/businesses")
    public ResponseEntity<BusinessDtoList> Businesses() {
        return customerService.findBusinesses();
    }


    @ApiOperation(value = "세탁소 상세 화면(조회만 가능)")
    @GetMapping("/{buId}")
    public ResponseEntity<BusinessDto> ReservationBusinessDetail(@PathVariable("buId") String buId) {
        return customerService.findBusiness(buId);
    }


}
