package project.laundry.service.owner.ReservationList;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.common.ClothStatus;
import project.laundry.data.request.OwnerReservationForm;
import project.laundry.data.response.ReservationDtoList;
import project.laundry.data.response.common.ReservationDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Reservation;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationListService {

    private final ReservationRepository reservationRepository;

    public ResponseEntity<ReservationDtoList> findReservationsByBusiness_id(String buId) {
        List<Reservation> reservations = reservationRepository.findReservationsByBusiness_uid(buId);

        List<ReservationDto> dto = reservations.stream().map(reservation -> {
            Business business = reservation.getBusiness();
            Customer customer = reservation.getCustomer();

            return ReservationDto.builder()
                    .re_id(reservation.getId())
                    .bu_id(business.getUid())
                    .cu_id(customer.getCustomer_id())
                    .cu_name(customer.getName())
                    .bu_name(business.getName())
                    .bu_address(business.getAddress())
                    .cu_phone(customer.getPhone())
                    .contact(business.getContact())
                    .cloth_status(reservation.getCloth_status())
                    .clothing_type(reservation.getClothing_type())
                    .request_detail(reservation.getRequest_detail())
                    .price(reservation.getPrice())
                    .createdAt(reservation.getCreateTime())
                    .build();
        }).collect(Collectors.toList());

        ReservationDtoList build = ReservationDtoList.builder().reservations(dto).build();

        return ResponseEntity.ok(build);
    }

    // 알림 기능 필요
    @Transactional
    public ResponseEntity<ReservationDto> updateReservation(OwnerReservationForm form, String buId, String reId) {
        Reservation reservation = reservationRepository.findById(reId).orElseThrow(EntityNotFoundException::new);

        reservation.setCloth_status(ClothStatus.valueOf(form.getCloth_status()));
        reservation.setPrice(form.getPrice());

        Reservation savedReservation = reservationRepository.save(reservation);

        Customer customer = savedReservation.getCustomer();
        Business business = savedReservation.getBusiness();

        ReservationDto build = ReservationDto.builder()
                .re_id(reId)
                .bu_id(business.getUid())
                .cu_id(customer.getUid())
                .cu_name(customer.getName())
                .bu_name(business.getName())
                .bu_address(business.getAddress())
                .cu_phone(customer.getPhone())
                .contact(business.getContact())
                .clothing_type(savedReservation.getClothing_type())
                .cloth_status(savedReservation.getCloth_status())
                .price(savedReservation.getPrice())
                .request_detail(savedReservation.getRequest_detail())
                .createdAt(savedReservation.getCreateTime())
                .build();


        return ResponseEntity.ok(build);
    }
}
