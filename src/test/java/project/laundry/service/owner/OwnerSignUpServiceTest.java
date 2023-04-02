package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.entity.Owner;
import project.laundry.domain.form.signUpForm;
import project.laundry.repository.OwnerRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OwnerSignUpServiceTest {


    @Autowired OwnerSignUpService service;

    @Autowired OwnerRepository repository;

    @Test
    void save() {

        // given
        signUpForm s1 = new signUpForm();
        s1.setId("test");
        s1.setPassword("1234");
        s1.setName("홍길동");
        s1.setPhone("010-1234-5678");
        s1.setUserType("OW");

        // when
        ResponseEntity<ownerResponseStatus> response = service.save(s1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(repository.findByOwner_id("test")).isNotNull();
    }

    @Test
    void saveTest_withDuplicateId() {

        // given
        signUpForm s1 = new signUpForm();
        s1.setId("test");
        s1.setPassword("1234");
        s1.setName("홍길동");
        s1.setPhone("010-1234-5678");
        s1.setUserType("OW");

        Owner owner = Owner.builder()
                .owner_id("test")
                .password("password")
                .name("name")
                .phone("01011111111")
                .build();


        // when
        repository.save(owner);
        ResponseEntity<ownerResponseStatus> response = service.save(s1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getStatus()).isEqualTo(false);
        assertThat(response.getBody().getMessage()).isEqualTo("중복된 아이디 입니다.");
    }

    @Test
    void saveTest_Exception() {

        // when
        signUpForm s1 = new signUpForm();
        s1.setId(null);
        ResponseEntity<ownerResponseStatus> response = service.save(s1);


        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().getMessage()).isEqualTo("올바르지 않은 요청입니다.");
        assertThat(response.getBody().getStatus()).isEqualTo(false);
    }
}