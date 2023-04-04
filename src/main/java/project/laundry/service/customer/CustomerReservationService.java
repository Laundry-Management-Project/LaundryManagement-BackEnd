package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.customer.reservationDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.reservation;
import project.laundry.data.entity.status.ClothStatus;
import project.laundry.data.form.reservationForm;
import project.laundry.repository.BusinessRepository;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerReservationService {

    private final ReservationRepository reservationRepository;
    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;

    public ResponseEntity<List<reservationDto>> findReservationByCustomerUid(String uid) {


        return null;
    }

    public ResponseEntity<List<businessDto>> findAllBusinesses() {
        List<Business> businesses = businessRepository.findAll();

        List<businessDto> businessDtos = businesses.stream().map(business -> businessDto.builder()
                .id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .build()).collect(Collectors.toList());

        return ResponseEntity.ok(businessDtos);
    }


    public ResponseEntity<String> saveReservation(reservationForm form, String uid) {

        Customer customer = customerRepository.findByCustomer_id(uid);
        Business business = businessRepository.findBusinessByBusiness_id(form.getBu_id());

        reservation build = reservation.builder()
                .customer(customer)
                .business(business)
                .clothCount(form.getClothCount())
                .clothStatus(ClothStatus.WASH_BEFORE)
                .content(form.getContent())
                .build();


        reservationRepository.save(build);

        return ResponseEntity.ok("ok");
    }

}
