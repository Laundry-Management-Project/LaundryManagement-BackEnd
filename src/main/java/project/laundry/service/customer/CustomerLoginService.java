package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Reservation;
import project.laundry.data.form.loginForm;
import project.laundry.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerLoginService {

    private final CustomerRepository customerRepository;

    public ResponseEntity<customerLoginDto> authenticateCustomerLogin(loginForm form) {

        Customer customer = customerRepository.findByCustomer_idAndPassword(form.getId(), form.getPassword());

        customerLoginDto rs = CreateCustomerLoginStatus(customer);

        if(!rs.getStatus()) {
            return ResponseEntity.badRequest().body(rs);
        }

        return ResponseEntity.ok(rs);
    }

    private customerLoginDto CreateCustomerLoginStatus(Customer customer) {

        if(customer == null) {
            return new customerLoginDto("가입되지 않은 사용자 입니다.", false, null);
        }

        List<Reservation> Reservations = customer.getReservations();

        List<reservationDto> dto = Reservations.stream().map(Reservation -> {
            Business business = Reservation.getBusiness();

            return reservationDto.builder()
                    .id(Reservation.getId())
                    .cu_name(customer.getName())
                    .bu_name(business.getName())
                    .bu_address(business.getAddress())
                    .clothStatus(Reservation.getClothStatus().getStatus())
                    .clothCount(Reservation.getClothCount())
                    .content(Reservation.getContent())
                    .build();

        }).collect(Collectors.toList());


        customerLoginDto rs = new customerLoginDto("로그인이 완료되었습니다.", true, customer.getId());
        rs.setReservations(dto);

        return rs;
    }

}
