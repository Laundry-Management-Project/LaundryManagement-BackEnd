package project.laundry.service.owner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.dto.owner.ownerLoginDto;
import project.laundry.data.entity.Owner;
import project.laundry.data.form.loginForm;
import project.laundry.repository.OwnerRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OwnerLoginServiceTest {

    @Autowired
    OwnerLoginService service;
    @Autowired
    OwnerRepository ownerRepository;

    @Test
    void authenticateOwner_Login() {

        // given
        Owner owner = Owner.builder()
                .owner_id("test")
                .password("1234")
                .name("name")
                .phone("01011111111")
                .build();

        Owner ow = ownerRepository.save(owner);


        loginForm form = new loginForm();
        form.setId("test");
        form.setPassword("1234");

        // then
        ResponseEntity<ownerLoginDto> response = service.authenticateOwnerLogin(form);

        // when
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getUid()).isEqualTo(ow.getId());
    }

    @Test
    void authenticateOwner_WithUserNullPointerException() {

        // given
        Owner owner = Owner.builder()
                .owner_id("test")
                .password("1234")
                .name("name")
                .phone("01011111111")
                .build();

        loginForm form = new loginForm();
        form.setId("test");
        form.setPassword("3213213");

        Owner ow = ownerRepository.save(owner);

        //then
        ResponseEntity<ownerLoginDto> response = service.authenticateOwnerLogin(form);


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getUid()).isNull();

    }
}