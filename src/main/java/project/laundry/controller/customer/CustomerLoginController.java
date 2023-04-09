package project.laundry.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.form.loginForm;
import project.laundry.service.customer.CustomerLoginService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class CustomerLoginController {

    private final CustomerLoginService customerLoginService;

    @ApiIgnore
    @GetMapping("/cu")
    public ResponseEntity<String> login() {

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/cu")
    public ResponseEntity<customerLoginDto> customer_login(@RequestBody loginForm form) {

        return customerLoginService.authenticateCustomerLogin(form);
    }

}
