package project.laundry.service.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.form.loginForm;
import project.laundry.data.form.signUpForm;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class CustomerLoginServiceTest {

    @Autowired
    CustomerSignUpService service;
    @Autowired
    CustomerLoginService loginService;

    @Test
    void authenticateCustomerLogin() {

        signUpForm form = new signUpForm();
        form.setId("test");
        form.setName("홍길동");
        form.setPassword("1234");
        form.setPhone("010-1234-5678");

        service.save(form);

        loginForm loginForm = new loginForm();
        loginForm.setId("test");
        loginForm.setPassword("1234");

        ResponseEntity<customerLoginDto> response = loginService.authenticateCustomerLogin(loginForm);

        System.out.println("response.getBody().getReservations() = " + response.getBody().getReservations());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}