package project.laundry.controller.owner;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.form.signUpForm;
import project.laundry.service.customer.CustomerSignUpService;
import project.laundry.service.owner.OwnerSignUpService;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
@Slf4j
public class OwnerSignUpController {

    private final CustomerSignUpService customerSignUpService;

    private final OwnerSignUpService ownerSignUpService;

    @GetMapping("/ow")
    public ResponseEntity<String> signUp() {

        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "사장님 회원가입을 위한 메소드")
    @ApiImplicitParam(name = "form", value = "id, password, name, phone, userType(사용X)", dataType = "signUpForm")
    @PostMapping("/ow")
    public ResponseEntity<ownerResponseStatus> owner_signup(@RequestBody @Validated signUpForm form, BindingResult br) {
        if(br.hasErrors()) {
            log.error(br.toString());
            ownerResponseStatus rs = new ownerResponseStatus("잘못된 입력입니다.", false, null);
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }
        return ownerSignUpService.save(form);
    }

}

