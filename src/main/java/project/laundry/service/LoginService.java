package project.laundry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.dto.login.loginDto;
import project.laundry.dto.status.responseStatus;
import project.laundry.entity.Customer;
import project.laundry.entity.Owner;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;

    public ResponseEntity<responseStatus> authenticateLogin(loginDto dto) {

        if (dto.getLoginType().equals("CU")) {
            Customer customer = customerRepository.findByCustomer_idAndPassword(dto.getId(), dto.getPassword());

            if(customer == null) {
                responseStatus rs = new responseStatus("가입되지 않은 사용자 입니다.", false);
                return ResponseEntity.badRequest().body(rs);
            }
            responseStatus rs = new responseStatus("로그인이 완료되었습니다.", true, customer.getId());
            return ResponseEntity.ok(rs);
        }

        if(dto.getLoginType().equals("OW")) {
            Owner owner = ownerRepository.findByOwner_idAndPassword(dto.getId(), dto.getPassword());

            if(owner == null) {
                responseStatus rs = new responseStatus("가입되지 않은 사용자 입니다.", false);
                return ResponseEntity.badRequest().body(rs);
            }
            responseStatus rs = new responseStatus("로그인이 완료되었습니다.", true, owner.getId());
            return ResponseEntity.ok(rs);
        }

        responseStatus rs = new responseStatus("회원 가입에 실패 했습니다.", false);
        return ResponseEntity.internalServerError().body(rs);
    }


}
