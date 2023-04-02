package project.laundry.controller.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.laundry.domain.dto.businessDto;
import project.laundry.service.customer.CustomerReservationService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerReservationService reservationService;

    @GetMapping("/{uid}/reservation")
    public ResponseEntity<List<businessDto>> customerReservationPage(@PathVariable("uid") String uid) {

        return reservationService.findBusinessesByCustomerUid(uid);
    }


}
