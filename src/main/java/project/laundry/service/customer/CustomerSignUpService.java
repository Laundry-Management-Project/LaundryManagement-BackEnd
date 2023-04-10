package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Customer;
import project.laundry.data.dto.common.signUpDto;
import project.laundry.data.form.signUpForm;
import project.laundry.exception.DuplicateUserException;
import project.laundry.repository.CustomerRepository;
import project.laundry.service.common.FormValidator;
import project.laundry.service.common.SignUpService;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerSignUpService implements SignUpService {

    private final CustomerRepository customerRepository;
    private final FormValidator validator;
    @Transactional
    public ResponseEntity<signUpDto> save(signUpForm form) {

        validator.signUpValidate(form);

        if(validator.isDuplicateCustomer(form.getId())) {
            throw new DuplicateUserException();
        }

        Customer customerEntity = buildCustomerEntity(form);
        Customer customer = customerRepository.save(customerEntity);
        signUpDto rs = new signUpDto("회원 가입이 완료 되었습니다.", true, customer.getUid());
        return ResponseEntity.ok(rs);
    }


    private Customer buildCustomerEntity(signUpForm dto) {

        return Customer.builder()
                .customer_id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }
}
