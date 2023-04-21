package project.laundry.service.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.response.common.SignupDto;
import project.laundry.data.request.signUpForm;
import project.laundry.service.common.Signup.CustomerSignUpService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerSignUpServiceTest {

    @Autowired
    CustomerSignUpService service;

    @Test
    void save() {
        signUpForm form = new signUpForm();
        form.setId("test");
        form.setName("홍길동");
        form.setPassword("1234");
        form.setPhone("010-1234-5678");

        ResponseEntity<SignupDto> response = service.save(form);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getStatus()).isTrue();
    }
}