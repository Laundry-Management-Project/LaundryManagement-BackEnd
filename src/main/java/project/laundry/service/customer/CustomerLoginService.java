package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Reservation;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.data.dto.customer.customerLoginDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.form.loginForm;
import project.laundry.repository.BusinessRepository;
import project.laundry.repository.CustomerRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerLoginService {

    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public ResponseEntity<customerLoginDto> authenticateCustomerLogin(loginForm form) {

        Customer customer = customerRepository.findByCustomer_idAndPassword(form.getId(), form.getPassword());

        customerLoginDto rs = createCustomerLoginStatus(customer);

        return rs.getStatus() ? ResponseEntity.ok(rs) : ResponseEntity.badRequest().body(rs);
    }

    public customerLoginDto createCustomerLoginStatus(Customer customer) {
        List<Business> businesses = businessRepository.findAll();

        if(customer == null) {
            return new customerLoginDto("가입되지 않은 사용자 입니다.", false, null);
        }

        // reservation null 체크
        List<Reservation> reservations = Optional.ofNullable(customer.getReservations()).orElse(Collections.emptyList());

        // 지연 로딩(LAZY)이므로 쿼리가 1번 나간다.
        // 해당 손님의 예약 목록 DTO Builder
        List<reservationDto> reservationDto = reservations.stream().map(reservation -> buildReservationDto(customer, reservation)).collect(Collectors.toList());

        // 모든 매장 목록 DTO Builder
        List<businessDto> businessDto = businesses.stream().map(this::buildBusinessDto).collect(Collectors.toList());

        customerLoginDto rs = new customerLoginDto("로그인이 완료되었습니다.", true, customer.getUid());
        rs.setReservations(reservationDto);
        rs.setBusinesses(businessDto);

        return rs;
    }

    public reservationDto buildReservationDto(Customer customer, Reservation reservation) {
        Business business = reservation.getBusiness();

        log.info("reservation.getBu_name() : {}", reservation.getBu_name());

        return reservationDto.builder()
                .id(reservation.getId())
                .bu_id(business.getUid())
                .cu_name(customer.getName())
                .bu_name(business.getName())
                .bu_address(business.getAddress())
                .clothStatus(reservation.getClothStatus().getStatus())
                .clothCount(reservation.getClothCount())
                .content(reservation.getContent())
                .createdAt(reservation.getCreateTime())
                .build();
    }

    private businessDto buildBusinessDto(Business business) {

        return businessDto.builder()
                .id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .build();
    }
}
