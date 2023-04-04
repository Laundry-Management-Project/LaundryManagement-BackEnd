package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.dto.customer.customerSignUpDto;
import project.laundry.data.form.signUpForm;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.entity.Customer;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.exception.FormNullPointerException;
import project.laundry.repository.CustomerRepository;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerSignUpService {

    private final CustomerRepository customerRepository;

    @Transactional
    public ResponseEntity<customerSignUpDto> save(signUpForm form) {

        try {

            // form에 있는 값이 null이 발생하면 throw FormNullPointerException
            if(form == null || Stream.of(form.getId(),  form.getPassword(), form.getPhone(), form.getName()).anyMatch(Objects::isNull)) {
                throw new FormNullPointerException();
            }

            Customer customerEntity = customerEntity(form);

            if(isDuplicateCustomer(form.getId())) {
                customerSignUpDto rs = new customerSignUpDto("중복된 아이디 입니다.", false, customerEntity.getId());
                return ResponseEntity.badRequest().body(rs);
            }


            Customer customer = customerRepository.save(customerEntity);
            customerSignUpDto rs = new customerSignUpDto("회원 가입이 완료 되었습니다.", true, customer.getId());
            return ResponseEntity.ok(rs);

        } catch (DataIntegrityViolationException e) {
            customerSignUpDto rs = new customerSignUpDto("회원 가입에 실패 했습니다.", false, null);
            return ResponseEntity.internalServerError().body(rs);
        } catch (FormNullPointerException e) {
            customerSignUpDto rs = new customerSignUpDto("올바르지 않은 요청입니다.", false, null);
            return ResponseEntity.internalServerError().body(rs);
        }
    }


    private boolean isDuplicateCustomer(String customer_id) {
        return customerRepository.findByCustomer_uid(customer_id) != null;
    }


    private Customer customerEntity(signUpForm dto) {

        return Customer.builder()
                .customer_id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }
}
