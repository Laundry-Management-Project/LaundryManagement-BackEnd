package project.laundry.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.form.loginForm;
import project.laundry.service.customer.CustomerLoginService;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class CustomerLoginController {

    private final CustomerLoginService customerLoginService;

    @GetMapping("/cu")
    public ResponseEntity<String> login() {

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/cu")
    public ResponseEntity<customerLoginDto> customer_login(@RequestBody @Validated loginForm form, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            return ResponseEntity.badRequest().body(null);
        }

        return customerLoginService.authenticateCustomerLogin(form);
    }

}
