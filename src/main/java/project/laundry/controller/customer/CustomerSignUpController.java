package project.laundry.controller.customer;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.form.signUpForm;
import project.laundry.service.customer.CustomerSignUpService;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
@Slf4j
public class CustomerSignUpController {

    private final CustomerSignUpService customerSignUpService;

    @GetMapping("/cu")
    public ResponseEntity<String> signUp() {

        return ResponseEntity.ok("ok");
    }


    @ApiOperation(value = "손님 회원가입을 위한 메소드")
    @ApiImplicitParam(name = "form", value = "id, password, name, phone, userType(사용X)", dataType = "signUpForm")
    @PostMapping("/cu")
    public ResponseEntity<customerLoginDto> customer_signUp(@RequestBody @Validated signUpForm form, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            customerLoginDto rs = new customerLoginDto("잘못된 입력입니다.", false, null);
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }

        return customerSignUpService.save(form);
    }
}
