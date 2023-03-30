package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.domain.dto.status.customerResponseStatus;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.entity.Customer;
import project.laundry.domain.entity.Owner;
import project.laundry.domain.form.signUpForm;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerSignUpService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public ResponseEntity<ownerResponseStatus> save(signUpForm form) {

        try {

            if(form.getUserType().equals("OW")) {

                Owner ownerEntity = ownerEntity(form);

                if(isDuplicateOwner(form.getId())) {
                    ownerResponseStatus rs = new ownerResponseStatus("중복된 아이디 입니다.", false, ownerEntity.getId());
                    return ResponseEntity.badRequest().body(rs);
                }

                ownerResponseStatus rs = new ownerResponseStatus("회원 가입이 완료 되었습니다.", true, ownerEntity.getId());
                ownerRepository.save(ownerEntity);

                return ResponseEntity.ok(rs);
            }

            ownerResponseStatus rs = new ownerResponseStatus("회원 가입에 실패 했습니다.", false, null);
            return ResponseEntity.internalServerError().body(rs);
        } catch (DataIntegrityViolationException e) {
            ownerResponseStatus rs = new ownerResponseStatus("회원 가입에 실패 했습니다.", false, null);
            return ResponseEntity.internalServerError().body(rs);
        }
    }


    private boolean isDuplicateOwner(String owner_id) {
        return ownerRepository.findByOwner_id(owner_id) != null;
    }


    private Owner ownerEntity(signUpForm dto) {
        return Owner.builder()
                .owner_id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }
}
