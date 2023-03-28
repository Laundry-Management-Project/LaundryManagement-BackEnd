package project.laundry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.dto.signup.signUpDto;
import project.laundry.dto.status.responseStatus;
import project.laundry.entity.Customer;
import project.laundry.entity.Owner;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SignUpService {

    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public ResponseEntity<responseStatus> save(signUpDto dto) {

        try {
            if(dto.getUserType().equals("CU")) {

                if(isDuplicateCustomer(dto.getId())) {
                    responseStatus rs = new responseStatus("중복된 아이디 입니다.", false);
                    return ResponseEntity.badRequest().body(rs);
                }

                responseStatus rs = new responseStatus("회원 가입이 완료 되었습니다.", true);
                Customer customerEntity = customerEntity(dto);
                customerRepository.save(customerEntity);

                return ResponseEntity.ok(rs);

            }

            if(dto.getUserType().equals("OW")) {

                if(isDuplicateOwner(dto.getId())) {
                    responseStatus rs = new responseStatus("중복된 아이디 입니다.", false);
                    return ResponseEntity.badRequest().body(rs);
                }

                responseStatus rs = new responseStatus("회원 가입이 완료 되었습니다.", true);
                Owner ownerEntity = ownerEntity(dto);
                ownerRepository.save(ownerEntity);

                return ResponseEntity.ok(rs);
            }

            responseStatus rs = new responseStatus("회원 가입에 실패 했습니다.", false);
            return ResponseEntity.internalServerError().body(rs);
        } catch (DataIntegrityViolationException e) {
            responseStatus rs = new responseStatus("회원 가입에 실패 했습니다.", false);
            return ResponseEntity.internalServerError().body(rs);
        }
    }



    private boolean isDuplicateCustomer(String customer_id) {
        return customerRepository.findByCustomer_id(customer_id) != null;
    }


    private boolean isDuplicateOwner(String owner_id) {
        return ownerRepository.findByOwner_id(owner_id) != null;
    }


    private Customer customerEntity(signUpDto dto) {

        return Customer.builder()
                .customer_id(dto.getId())
                .name(dto.getName())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
    }

    private Owner ownerEntity(signUpDto dto) {
        return Owner.builder()
                .owner_id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }
}
