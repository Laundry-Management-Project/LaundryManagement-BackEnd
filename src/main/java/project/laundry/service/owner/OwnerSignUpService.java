package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.dto.common.signUpDto;
import project.laundry.data.entity.Owner;
import project.laundry.data.form.signUpForm;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.exception.FormNullPointerException;
import project.laundry.repository.OwnerRepository;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerSignUpService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public ResponseEntity<signUpDto> save(signUpForm form) {

        try {

            validForm(form);

            Owner ownerEntity = ownerEntity(form);

            // 아이디 중복 체크
            if(isDuplicateOwner(form.getId())) {
                signUpDto rs = new signUpDto("중복된 아이디 입니다.", false, "");
                return ResponseEntity.badRequest().body(rs);
            }

            Owner owner = ownerRepository.save(ownerEntity);
            signUpDto rs = new signUpDto("회원 가입이 완료 되었습니다.", true, owner.getId());

            return ResponseEntity.ok(rs);

        } catch (DataIntegrityViolationException e) {
            signUpDto rs = new signUpDto("회원 가입에 실패 했습니다.", false, null);
            return ResponseEntity.badRequest().body(rs);
        } catch (FormNullPointerException e) {
            signUpDto rs = new signUpDto("올바르지 않은 요청입니다.", false, null);
            return ResponseEntity.internalServerError().body(rs);
        }
    }

    private void validForm(signUpForm form) {
        // form에 있는 값이 null이 발생하면 throw FormNullPointerException
        if(form == null || Stream.of(form.getId(),  form.getPassword(), form.getPhone(), form.getName()).anyMatch(Objects::isNull)) {
            throw new FormNullPointerException();
        }
    }


    private boolean isDuplicateOwner(String owner_id) {
        return ownerRepository.findByOwner_id(owner_id) != null;
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
