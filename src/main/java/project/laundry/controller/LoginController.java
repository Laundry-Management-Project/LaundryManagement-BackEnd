package project.laundry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.dto.login.loginDto;
import project.laundry.dto.status.responseStatus;
import project.laundry.service.LoginService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<String> login() {

       return ResponseEntity.ok("ok");
    }

    @PostMapping("/login")
    public ResponseEntity<responseStatus> post_login(@RequestBody @Validated loginDto dto, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            responseStatus rs = new responseStatus("잘못된 입력 입니다.", false);
            return ResponseEntity.badRequest().body(rs);
        }

        return loginService.authenticateLogin(dto);
    }
}
