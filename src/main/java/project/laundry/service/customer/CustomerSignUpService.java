package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.form.signUpForm;
import project.laundry.domain.dto.status.customerResponseStatus;
import project.laundry.domain.entity.Customer;
import project.laundry.domain.entity.Owner;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerSignUpService {

    private final CustomerRepository customerRepository;

    @Transactional
    public ResponseEntity<customerResponseStatus> save(signUpForm form) {

        try {
            Customer customer = customerEntity(form);

            if(isDuplicateCustomer(form.getId())) {
                customerResponseStatus rs = new customerResponseStatus("중복된 아이디 입니다.", false, customer.getId());
                return ResponseEntity.badRequest().body(rs);
            }


            customerRepository.save(customer);
            customerResponseStatus rs = new customerResponseStatus("회원 가입이 완료 되었습니다.", true, customer.getId());
            return ResponseEntity.ok(rs);

        } catch (DataIntegrityViolationException e) {
            customerResponseStatus rs = new customerResponseStatus("회원 가입에 실패 했습니다.", false, form.getId());
            return ResponseEntity.internalServerError().body(rs);
        }
    }


    private boolean isDuplicateCustomer(String customer_id) {
        return customerRepository.findByCustomer_id(customer_id) != null;
    }


    private Customer customerEntity(signUpForm dto) {

        return Customer.builder()
                .customer_id(dto.getId())
                .name(dto.getName())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
    }
}
