package project.laundry.controller.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.form.loginForm;
import project.laundry.service.owner.OwnerLoginService;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class OwnerLoginController {

    private final OwnerLoginService ownerLoginService;

    @GetMapping("/ow")
    public ResponseEntity<String> login() {

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/ow")
    public ResponseEntity<ownerResponseStatus> owner_login(@RequestBody @Validated loginForm form, BindingResult br) {

        if(br.hasErrors()) {
            log.error(br.toString());
            return ResponseEntity.badRequest().body(null);
        }

        return ownerLoginService.authenticateOwnerLogin(form);

    }


}
