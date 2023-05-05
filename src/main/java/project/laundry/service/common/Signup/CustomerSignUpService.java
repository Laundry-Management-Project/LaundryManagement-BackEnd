package project.laundry.service.common.Signup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Customer_Authority;
import project.laundry.data.response.common.SignupDto;
import project.laundry.data.request.signUpForm;
import project.laundry.exception.DuplicateUserException;
import project.laundry.repository.CustomerRepository;

import java.util.Collections;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerSignUpService implements SignUpService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<SignupDto> save(signUpForm form) {

        Customer customer = CustomerEntity(form);

        SignupDto rs = new SignupDto("회원 가입이 완료 되었습니다.", true);
        rs.setUid(customer.getUid());

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    private Customer CustomerEntity(signUpForm form) {
        // 비밀번호 단방향 해시 암호화
        String encPwd = passwordEncoder.encode(form.getPassword());

        Customer customer = Customer.builder()
                .customer_id(form.getId())
                .password(encPwd)
                .name(form.getName())
                .phone(form.getPhone())
                .build();

        customer.setRoles(Collections.singletonList(Customer_Authority.builder().name("USER_CUSTOMER").build()));

        return customerRepository.save(customer);
    }
}
