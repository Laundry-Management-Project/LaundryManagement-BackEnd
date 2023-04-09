package project.laundry.controller.owner;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.common.signUpDto;
import project.laundry.data.form.signUpForm;
import project.laundry.service.owner.OwnerSignUpService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
@Slf4j
public class OwnerSignUpController {

    private final OwnerSignUpService ownerSignUpService;

    @ApiIgnore
    @GetMapping("/ow")
    public ResponseEntity<String> signUp() {

        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "사장님 회원가입을 위한 메소드")
    @ApiImplicitParam(name = "signUpForm", value = "id, password, name, phone, userType(사용X)", dataType = "Object")
    @PostMapping("/ow")
    public ResponseEntity<signUpDto> owner_signup(@RequestBody signUpForm form) {
        return ownerSignUpService.save(form);
    }

}

