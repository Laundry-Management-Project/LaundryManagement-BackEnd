package project.laundry.controller.customer;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @PostMapping("/cu")
    public ResponseEntity<SignupDto> customer_signUp(
            @ApiParam(name = "form", value = "하단 signUpForm 참조", required = true) @RequestBody signUpForm form) {
        return customerSignUpService.save(form);
    }
}
