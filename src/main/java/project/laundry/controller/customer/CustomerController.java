package project.laundry.controller.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.form.reservationForm;
import project.laundry.service.customer.CustomerReservationService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerReservationService reservationService;

    @GetMapping("/{uid}/reservation/add")
    public ResponseEntity<List<businessDto>> customerReservationPage(@PathVariable String uid) {
        return reservationService.findAllBusinesses();
    }

    @PostMapping("/{uid}/reservation/add")
    public ResponseEntity<String> customerReservationAddPage(@RequestBody @Validated reservationForm form, BindingResult br) {

        if(br.hasErrors()) {
            ResponseEntity.badRequest().body("올바르지 않은 양식입니다.");
        }

        return reservationService.saveReservation(form);
    }


}
