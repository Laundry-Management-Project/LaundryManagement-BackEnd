package project.laundry.service.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.form.signUpForm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CustomerSignUpServiceTest {

    @Autowired CustomerSignUpService service;

    @Test
    void save() {
        signUpForm form = new signUpForm();
        form.setId("test");
        form.setName("홍길동");
        form.setPassword("1234");
        form.setPhone("010-1234-5678");

        ResponseEntity<customerLoginDto> response = service.save(form);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getStatus()).isTrue();
    }
}