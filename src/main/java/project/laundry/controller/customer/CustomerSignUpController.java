package project.laundry.controller.customer;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.request.signUpForm;
import project.laundry.data.response.common.SignupDto;
import project.laundry.service.common.Signup.CustomerSignUpService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
@Slf4j
public class CustomerSignUpController {

    private final CustomerSignUpService customerSignUpService;

    @ApiIgnore
    @GetMapping("/cu")
    public ResponseEntity<String> signUp() {

        return ResponseEntity.ok("ok");
    }


    @ApiOperation(value = "손님 회원가입을 위한 메소드")
    @ApiImplicitParam(name = "form", value = "id, password, name, phone, userType(사용X)", dataType = "signUpForm")
    @PostMapping("/cu")
    public ResponseEntity<SignupDto> customer_signUp(@RequestBody signUpForm form) {
        return customerSignUpService.save(form);
    }
}
