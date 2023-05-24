package project.laundry.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.request.loginForm;
import project.laundry.data.response.common.LoginDto;
import project.laundry.service.common.Login.LoginService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService service;

    @ApiIgnore
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("login - GET - 200");
    }

    @ApiOperation(value = "손님/사장 회원가입을 위한 메소드")
    @PostMapping("/login")
    public ResponseEntity<LoginDto> POST_login(
            @ApiParam(name = "form", value = "하단 loginForm 참조", required = true) @RequestBody loginForm form) {
        return service.authenticateLogin(form);
    }
}
