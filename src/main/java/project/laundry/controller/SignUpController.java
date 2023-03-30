package project.laundry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.form.signUpForm;
import project.laundry.domain.dto.status.customerResponseStatus;
import project.laundry.service.customer.CustomerSignUpService;
import project.laundry.service.owner.OwnerSignUpService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignUpController {

    private final CustomerSignUpService customerSignUpService;

    private final OwnerSignUpService ownerSignUpService;

    @GetMapping("/signup")
    public ResponseEntity<String> signUp() {

        return ResponseEntity.ok("ok");
    }


    @PostMapping("/signup/cu")
    public ResponseEntity<customerResponseStatus> customer_signUp(@RequestBody @Validated signUpForm form, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            customerResponseStatus rs = new customerResponseStatus("잘못된 입력입니다.", false, null);
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }

        return customerSignUpService.save(form);
    }


    @PostMapping("/signup/ow")
    public ResponseEntity<ownerResponseStatus> owner_signup(@RequestBody @Validated signUpForm form, BindingResult br) {
        if(br.hasErrors()) {
            log.error(br.toString());
            ownerResponseStatus rs = new ownerResponseStatus("잘못된 입력입니다.", false, null);
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }
        return ownerSignUpService.save(form);
    }

}

