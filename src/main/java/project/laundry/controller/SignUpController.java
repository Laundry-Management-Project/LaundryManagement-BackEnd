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
import project.laundry.dto.signup.signUpDto;
import project.laundry.dto.status.responseStatus;
import project.laundry.service.SignUpService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignUpController {

    private final SignUpService service;

    @GetMapping("/signup")
    public ResponseEntity<String> signUp() {

        return ResponseEntity.ok("ok");
    }


    @PostMapping("/signup")
    public ResponseEntity<responseStatus> post_signUp(@RequestBody @Validated signUpDto dto, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            responseStatus rs = new responseStatus("잘못된 입력입니다.", false);
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }

        return service.save(dto);

    }

}

