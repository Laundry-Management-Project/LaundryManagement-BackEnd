package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.dto.status.customerResponseStatus;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.entity.Business;
import project.laundry.domain.entity.Customer;
import project.laundry.domain.entity.Owner;
import project.laundry.domain.form.loginForm;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerLoginService {

    private final CustomerRepository customerRepository;

    public ResponseEntity<customerResponseStatus> authenticateCustomerLogin(loginForm form) {

        Customer customer = customerRepository.findByCustomer_idAndPassword(form.getId(), form.getPassword());

        customerResponseStatus rs = CreateCustomerLoginStatus(customer);

        return ResponseEntity.ok(rs);
    }

    private customerResponseStatus CreateCustomerLoginStatus(Customer customer) {
        if(customer == null) {
            return new customerResponseStatus("가입되지 않은 사용자 입니다.", false, null);
        }
        return new customerResponseStatus("로그인이 완료되었습니다.", true, customer.getId());
    }

}
