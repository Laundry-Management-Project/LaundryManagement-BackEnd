package project.laundry.service.common.Signup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Customer;
import project.laundry.data.response.common.SignupDto;
import project.laundry.data.request.signUpForm;
import project.laundry.exception.DuplicateUserException;
import project.laundry.repository.CustomerRepository;
import project.laundry.service.common.FormValidator;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerSignUpService implements SignUpService {

    private final CustomerRepository customerRepository;
    private final FormValidator validator;

//    private final BCryptPasswordEncoder cryptEncoder;

    @Transactional
    public ResponseEntity<SignupDto> save(signUpForm form) {

        validator.signUpValidate(form);

        if(validator.isDuplicateCustomer(form.getId())) {
            throw new DuplicateUserException();
        }

        Customer customerEntity = buildCustomerEntity(form);
        Customer customer = customerRepository.save(customerEntity);
        SignupDto rs = new SignupDto("회원 가입이 완료 되었습니다.", true);
        rs.setUid(customer.getUid());
        return ResponseEntity.ok(rs);
    }


    private Customer buildCustomerEntity(signUpForm form) {
        // 비밀번호 단방향 해시 암호화
//        String encPwd = cryptEncoder.encode(form.getPassword());

        return Customer.builder()
                .customer_id(form.getId())
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .role("ROLE_USER")
                .build();
    }
}
