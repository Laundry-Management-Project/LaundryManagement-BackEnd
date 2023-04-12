package project.laundry.controller.owner;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.owner.ownerLoginDto;
import project.laundry.data.form.loginForm;
import project.laundry.service.owner.OwnerLoginService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class OwnerLoginController {

    private final OwnerLoginService ownerLoginService;

    @ApiIgnore
    @GetMapping("/ow")
    public ResponseEntity<String> login() {

        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "손님 회원가입을 위한 메소드")
    @ApiImplicitParam(name = "form", value = "id, password", dataType = "loginForm")
    @PostMapping("/ow")
    public ResponseEntity<ownerLoginDto> owner_login(@RequestBody loginForm form) {

        return ownerLoginService.authenticateOwnerLogin(form);

    }


}
