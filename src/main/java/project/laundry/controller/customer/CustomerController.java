package project.laundry.controller.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.data.form.reservationForm;
import project.laundry.data.dto.common.businessDto;
import project.laundry.service.customer.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{uid}/reservations")
    public ResponseEntity<List<reservationDto>> Reservations(@PathVariable String uid) {
        return customerService.findReservations(uid);
    }

    @GetMapping("/{uid}/reservation/{bu_id}")
    public ResponseEntity<businessDto> ReservationBusinessDetail(@PathVariable("bu_id") String bu_id, @PathVariable String uid) {
        return customerService.findBusiness(bu_id);
    }

    @PostMapping("/{uid}/reservation/{bu_id}/add")
    public ResponseEntity<String> ReservationAdd(@RequestBody @Validated reservationForm form, @PathVariable("uid") String uid) {

        return customerService.saveReservation(form, uid);
    }


}
