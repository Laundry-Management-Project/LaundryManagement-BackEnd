package project.laundry.service.common.Login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.response.common.LoginDto;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Owner;
import project.laundry.data.request.loginForm;
import project.laundry.exception.FormNullPointerException;
import project.laundry.exception.UserNullPointerException;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LoginService {

    private final OwnerRepository ownerRepository;
    private final CustomerRepository customerRepository;

    public ResponseEntity<LoginDto> authenticateLogin(loginForm form) {
        if(form == null || Stream.of(form.getId(), form.getPassword(), form.getUser_type()).anyMatch(Objects::isNull)) {
            throw new FormNullPointerException();
        }

        if (form.getUser_type().equals("cu")) {
            LoginDto loginDto = authenticCustomer(form);
            System.out.println("loginDto.getUid() = " + loginDto.getUid());
            System.out.println("loginDto.getMessage() = " + loginDto.getMessage());
            return ResponseEntity.ok(loginDto);
        } else if(form.getUser_type().equals("ow")){
            return ResponseEntity.ok(authenticOwner(form));
        } else {
            throw new IllegalArgumentException("Invalid userType: " + form.getUser_type());
        }
    }

    private LoginDto authenticCustomer(loginForm form) {

        Customer customer = customerRepository.findByCustomer_idAndPassword(form.getId(), form.getPassword());

        if(customer == null) {
            throw new UserNullPointerException();
        }

        LoginDto dto = new LoginDto("로그인이 완료되었습니다.", true);
        dto.setUid(customer.getUid());

        return dto;
    }

    private LoginDto authenticOwner(loginForm form) {
        Owner owner = ownerRepository.findByOwner_idAndPassword(form.getId(), form.getPassword());

        if(owner == null) {
            throw new UserNullPointerException();
        }

        LoginDto dto = new LoginDto("로그인이 완료되었습니다.", true);
        dto.setUid(owner.getUid());

        return dto;
    }
}
