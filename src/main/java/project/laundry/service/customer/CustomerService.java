package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.data.common.ClothStatus;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Reservation;
import project.laundry.data.form.reservationForm;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.repository.BusinessRepository;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.ReservationRepository;
import project.laundry.service.common.FormValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final ReservationRepository reservationRepository;
    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;

    private final FormValidator validator;


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


    public ResponseEntity<String> saveReservation(reservationForm form, String cu_id) {

        Customer customer = customerRepository.findByUid(cu_id);
        Business business = businessRepository.findBusinessByBusiness_id(form.getBu_id()).orElseThrow(EntityNotFoundException::new);

        validator.saveReservationValidate(customer, business);

        Reservation build = Reservation.builder()
                .cu_name(customer.getName())
                .bu_name(business.getName())
                .clothStatus(ClothStatus.WASH_BEFORE)
                .clothCount(form.getClothCount())
                .content(form.getContent())
                .customer(customer)
                .business(business)
                .build();


        reservationRepository.save(build);

        return ResponseEntity.ok("ok");
    }

    public ResponseEntity<businessDto> findBusiness(String bu_id) {
        Business business = businessRepository.findBusinessByBusiness_id(bu_id).orElseThrow(EntityNotFoundException::new);

        businessDto dto = businessDto.builder()
                .id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getAddress())
                .build();

        return ResponseEntity.ok(dto);

    }

}
