package project.laundry.service.owner.ReservationList;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.common.ClothStatus;
import project.laundry.data.response.common.ReservationDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Reservation;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationListService {

    private final ReservationRepository reservationRepository;

    public ResponseEntity<List<ReservationDto>> findReservationsByBusiness_id(String buId) {
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
                    .cloth_status(reservation.getClothStatus().getStatus())
                    .clothing_type(reservation.getClothing_type())
                    .request_detail(reservation.getRequest_detail())
                    .price(reservation.getPrice())
                    .createdAt(reservation.getCreateTime())
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }

    // 알림 기능 필요
    @Transactional
    public ResponseEntity<ReservationDto> updateReservation(ReservationDto form, String buId, String reId) {
        Reservation reservation = reservationRepository.findById(reId).orElseThrow(EntityNotFoundException::new);

        reservation.setClothStatus(ClothStatus.valueOf(form.getCloth_status()));
        reservation.setPrice(form.getPrice());

        reservationRepository.save(reservation);


        return ResponseEntity.ok(form);
    }
}
