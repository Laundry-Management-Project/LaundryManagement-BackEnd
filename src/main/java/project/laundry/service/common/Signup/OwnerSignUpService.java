package project.laundry.service.common.Signup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.response.common.SignupDto;
import project.laundry.data.entity.Owner;
import project.laundry.data.request.signUpForm;
import project.laundry.exception.DuplicateUserException;
import project.laundry.repository.OwnerRepository;
import project.laundry.service.common.FormValidator;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerSignUpService {

    private final OwnerRepository ownerRepository;
    private final FormValidator validator;

    @Transactional
    public ResponseEntity<SignupDto> save(signUpForm form) {


        validator.signUpValidate(form);

        Owner ownerEntity = ownerEntity(form);

        // 아이디 중복 체크
        if(validator.isDuplicateOwner(form.getId())) {
            throw new DuplicateUserException();
        }

        Owner owner = ownerRepository.save(ownerEntity);
        SignupDto rs = new SignupDto("회원 가입이 완료 되었습니다.", true);
        rs.setUid(owner.getUid());

        return ResponseEntity.ok(rs);

    }

    private Owner ownerEntity(signUpForm form) {
        return Owner.builder()
                .owner_id(form.getId())
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .build();
    }
}
