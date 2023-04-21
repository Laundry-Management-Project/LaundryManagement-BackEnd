package project.laundry.service.common.Signup;

import org.springframework.http.ResponseEntity;
import project.laundry.data.request.signUpForm;
import project.laundry.data.response.common.SignupDto;

public interface SignUpService {

    ResponseEntity<SignupDto> save(signUpForm form);
}
