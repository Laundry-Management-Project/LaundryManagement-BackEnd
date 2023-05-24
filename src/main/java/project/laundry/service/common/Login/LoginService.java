package project.laundry.service.common.Login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.config.auth.TokenProvider.CustomerJwtTokenProvider;
import project.laundry.config.auth.TokenProvider.OwnerJwtTokenProvider;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Owner;
import project.laundry.data.request.loginForm;
import project.laundry.data.response.common.LoginDto;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LoginService {

    private final PasswordEncoder encoder;
    private final CustomerJwtTokenProvider customerJwtTokenProvider;
    private final OwnerJwtTokenProvider ownerJwtTokenProvider;

    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;

    public ResponseEntity<LoginDto> authenticateLogin(loginForm form) {

        if (form.getUser_type().equals("cu")) {
            return new ResponseEntity<>(authenticCustomer(form), HttpStatus.OK);
        } else if(form.getUser_type().equals("ow")){
            return new ResponseEntity<>(authenticOwner(form), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Invalid userType: " + form.getUser_type());
        }
    }

    private LoginDto authenticCustomer(loginForm form) {
        Customer customer = customerRepository.findCustomerByCustomerId(form.getId());

        if(!encoder.matches(form.getPassword(), customer.getPassword())) {
            throw new BadCredentialsException("잘못된 계정 정보입니다.");
        }

        LoginDto dto = new LoginDto("로그인을 완료 했습니다.", true);
        dto.setUid(customer.getUid());
        dto.setToken(customerJwtTokenProvider.createToken(customer.getCustomer_id(), customer.getRoles()));

        return dto;
    }

    private LoginDto authenticOwner(loginForm form) {
        Owner owner = ownerRepository.findByOwner_id(form.getId());

        if(!encoder.matches(form.getPassword(), owner.getPassword())) {
            throw new BadCredentialsException("잘못된 계정 정보입니다.");
        }

        LoginDto dto = new LoginDto("로그인을 완료 했습니다.", true);
        dto.setUid(owner.getUid());
        dto.setToken(ownerJwtTokenProvider.createToken(owner.getOwner_id(), owner.getRoles()));

        return dto;
    }
}
