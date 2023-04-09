package project.laundry.service.common;

import org.springframework.http.ResponseEntity;
import project.laundry.data.form.signUpForm;
import project.laundry.data.dto.common.signUpDto;

public interface SignUpService {

    ResponseEntity<signUpDto> save(signUpForm form);
}
