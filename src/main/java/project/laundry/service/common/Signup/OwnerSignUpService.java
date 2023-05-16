package project.laundry.service.common.Signup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Owner_Authority;
import project.laundry.data.response.common.SignupDto;
import project.laundry.data.entity.Owner;
import project.laundry.data.request.signUpForm;
import project.laundry.repository.OwnerRepository;

import java.util.Collections;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerSignUpService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<SignupDto> save(signUpForm form) {

        Owner owner = ownerEntity(form);
        SignupDto rs = new SignupDto("회원 가입이 완료 되었습니다.", true);
        rs.setUid(owner.getUid());

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    private Owner ownerEntity(signUpForm form) {

        String encPwd = passwordEncoder.encode(form.getPassword());

        Owner owner = Owner.builder()
                .owner_id(form.getId())
                .password(encPwd)
                .name(form.getName())
                .phone(form.getPhone())
                .build();

        owner.setRoles(Collections.singletonList(Owner_Authority.builder().role("ROLE_USER_OWNER").build()));

        return ownerRepository.save(owner);
    }
}
