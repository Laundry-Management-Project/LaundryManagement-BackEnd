package project.laundry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.dto.login.loginDto;
import project.laundry.dto.status.responseStatus;
import project.laundry.entity.customer;
import project.laundry.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepository customerRepository;


    public ResponseEntity<responseStatus> authenticateLogin(loginDto dto) {

        customer customer = customerRepository.findCustomerByCustomer_idAndPassword(dto.getId(), dto.getPassword());

        if(customer == null) {
            responseStatus rs = new responseStatus("가입되지 않은 사용자 입니다.", false);
            return ResponseEntity.badRequest().body(rs);
        }

        responseStatus rs = new responseStatus("로그인이 완료되었습니다.", true, customer.getId());

        return ResponseEntity.ok(rs);
    }


}
