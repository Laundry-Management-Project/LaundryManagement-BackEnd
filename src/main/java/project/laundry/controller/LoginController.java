package project.laundry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.dto.status.customerResponseStatus;
import project.laundry.domain.form.loginForm;
import project.laundry.service.customer.CustomerLoginService;
import project.laundry.service.owner.OwnerLoginService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final OwnerLoginService ownerLoginService;

    private final CustomerLoginService customerLoginService;

    @GetMapping("/login")
    public ResponseEntity<String> login() {

       return ResponseEntity.ok("ok");
    }

    @PostMapping("/login/cu")
    public ResponseEntity<customerResponseStatus> customer_login(@RequestBody @Validated loginForm form, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            return ResponseEntity.badRequest().body(null);
        }

        return customerLoginService.authenticateCustomerLogin(form);
    }

    @PostMapping("/login/ow")
    public ResponseEntity<ownerResponseStatus> owner_login(@RequestBody @Validated loginForm form, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            return ResponseEntity.badRequest().body(null);
        }

        return ownerLoginService.authenticateOwnerLogin(form);

    }


}
