package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.common.ClothStatus;
import project.laundry.data.response.common.ReservationDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Reservation;
import project.laundry.data.request.reservationForm;
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
@Transactional(readOnly = true)
public class CustomerReservationService {

    private final ReservationRepository reservationRepository;
    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;
    private final FormValidator validator;

    public ResponseEntity<List<ReservationDto>> findReservations(String uId) {
        List<Reservation> reservations = reservationRepository.findReservationsByCustomer_uid(uId);


        List<ReservationDto> collect = reservations.stream().map(reservation -> {
            Business business = reservation.getBusiness();
            Customer customer = reservation.getCustomer();

            return ReservationDto.builder()
                    .re_id(reservation.getId())
                    .bu_id(business.getUid())
                    .cu_id(uId)
                    .cu_name(customer.getName())
                    .bu_name(business.getName())
                    .bu_address(business.getAddress())
                    .cu_phone(customer.getPhone())
                    .contact(business.getContact())
                    .cloth_status(reservation.getClothStatus().getStatus())
                    .request_detail(reservation.getRequest_detail())
                    .clothing_type(reservation.getClothing_type())
                    .price(reservation.getPrice())
                    .createdAt(reservation.getCreateTime())
                    .build();
        }).collect(Collectors.toList());

//        ReservationDtoList build = ReservationDtoList.builder().reservations(collect).build();

        return ResponseEntity.ok(collect);
    }

    @Transactional
    public ResponseEntity<ReservationDto> saveReservation(reservationForm form, String buId, String uId) {

        Customer customer = customerRepository.findByUid(uId);
        Business business = businessRepository.findBusinessByBusiness_id(buId).orElseThrow(EntityNotFoundException::new);

        validator.saveReservationValidate(customer, business);

        Reservation reservationBuilder = Reservation.builder()
                .clothStatus(ClothStatus.WASH_BEFORE)
                .request_detail(form.getRequest_detail())
                .clothing_type(form.getClothing_type())
                .price(0)
                .customer(customer)
                .business(business)
                .build();

        Reservation savedReservation = reservationRepository.save(reservationBuilder);

        ReservationDto reservationDtoBuilder = ReservationDto.builder()
                .re_id(savedReservation.getId())
                .bu_id(business.getUid())
                .cu_id(customer.getUid())
                .cu_name(customer.getName())
                .bu_name(business.getName())
                .bu_address(business.getAddress())
                .cu_phone(customer.getPhone())
                .contact(business.getContact())
                .clothing_type(savedReservation.getClothing_type())
                .cloth_status(ClothStatus.WASH_BEFORE.getStatus())
                .price(savedReservation.getPrice())
                .request_detail(savedReservation.getRequest_detail())
                .createdAt(savedReservation.getCreateTime())
                .build();


        return ResponseEntity.ok(reservationDtoBuilder);
    }
}
