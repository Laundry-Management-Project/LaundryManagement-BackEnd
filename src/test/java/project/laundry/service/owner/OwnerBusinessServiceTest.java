package project.laundry.service.owner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.entity.Owner;
import project.laundry.domain.form.businessForm;
import project.laundry.repository.OwnerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class OwnerBusinessServiceTest {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    OwnerBusinessService service;

    @Test
    void saveBusiness() {

        // given
        Owner owner = Owner.builder()
                .owner_id("test")
                .password("password")
                .name("name")
                .phone("01011111111")
                .build();

        Owner ow = ownerRepository.save(owner);

        businessForm form = new businessForm();
        form.setName("블라블라 세탁소");
        form.setAddress("서울시 블라블라 아몰라");
        form.setBu_hour("08:00 - 22:00");

        ResponseEntity<businessDto> response = service.saveBusiness(form, ow.getId());


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(form.getName());
        assertThat(response.getBody().getAddress()).isEqualTo(form.getAddress());
        assertThat(response.getBody().getBu_hour()).isEqualTo(form.getBu_hour());
    }

    @Test
    void saveBusiness_withFormNullPointerException() {

        //given
        businessForm form = null;

        Owner owner = Owner.builder()
                .owner_id("test")
                .password("password")
                .name("name")
                .phone("01011111111")
                .build();

        Owner ow = ownerRepository.save(owner);

        // then
        ResponseEntity<businessDto> response = service.saveBusiness(form, ow.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    void saveBusiness_withUserNullPointerException() {
        businessForm form = new businessForm();
        form.setName("블라블라 세탁소");
        form.setAddress("서울시 블라블라 아몰라");
        form.setBu_hour("08:00 - 22:00");

        String id = null;

        ResponseEntity<businessDto> response = service.saveBusiness(form, id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}