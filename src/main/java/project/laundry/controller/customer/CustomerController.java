package project.laundry.controller.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.dto.reservationDto;
import project.laundry.domain.form.reservationForm;
import project.laundry.service.customer.CustomerReservationService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerReservationService reservationService;

    @ApiIgnore
    @GetMapping("/{uid}/reservation")
    public ResponseEntity<List<reservationDto>> customerReservationListPage(@PathVariable("uid") String uid) {

        return reservationService.findReservationByCustomerUid(uid);
    }

    @GetMapping("/{uid}/reservation/add")
    public ResponseEntity<List<businessDto>> customerReservationPage(@PathVariable String uid) {
        return reservationService.findAllBusinesses();
    }

    @PostMapping("/{uid}/reservation/add")
    public ResponseEntity<String> customerReservationAddPage(@RequestBody @Validated reservationForm form, BindingResult br, @PathVariable String uid) {

        if(br.hasErrors()) {
            return null;
        }

        return reservationService.saveReservation(form, uid);
    }


}
