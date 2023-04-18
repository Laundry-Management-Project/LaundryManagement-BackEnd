package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Owner;
import project.laundry.data.entity.Reservation;
import project.laundry.data.form.businessForm;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.exception.FormNullPointerException;
import project.laundry.exception.UserNullPointerException;
import project.laundry.repository.BusinessRepository;
import project.laundry.repository.OwnerRepository;
import project.laundry.repository.ReservationRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerService {

    private final BusinessRepository businessRepository;

    private final OwnerRepository ownerRepository;

    private final ReservationRepository reservationRepository;

    @Transactional
    public ResponseEntity<businessDto> saveBusiness(businessForm form, String owner_uid) {

        try {

            if(form == null || Stream.of(form.getName(), form.getAddress(), form.getBu_hour()).anyMatch(Objects::isNull)) {
                throw new FormNullPointerException();
            }
            if(owner_uid == null) {
                throw new UserNullPointerException();
            }

            Optional<Owner> ownerOptional = ownerRepository.findById(owner_uid);

            if(ownerOptional.isPresent()) {
                Owner owner = ownerOptional.get();
                Business business = Business.builder()
                        .name(form.getName())
                        .address(form.getAddress())
                        .bu_hour(form.getBu_hour())
                        .owner(owner)
                        .build();
                businessRepository.save(business);

                businessDto businessDto = entityToDto(business);

                return ResponseEntity.ok(businessDto);
            }
        } catch (FormNullPointerException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (UserNullPointerException e) {
            return ResponseEntity.internalServerError().body(null);
        }

        return ResponseEntity.internalServerError().body(null);
    }

    public ResponseEntity<List<reservationDto>> findReservationsByBusiness_id(String business_id) {
        List<Reservation> reservations = reservationRepository.findReservationsByBusiness_uid(business_id);

        List<reservationDto> dto = reservations.stream().map(reservation -> {
            Business business = reservation.getBusiness();
            return reservationDto.builder()
                    .id(reservation.getId())
                    .bu_id(business.getUid())
                    .cu_name(reservation.getCu_name())
                    .bu_name(business.getName())
                    .bu_address(business.getAddress())
                    .clothCount(reservation.getClothCount())
                    .clothStatus(reservation.getClothStatus().getStatus())
                    .content(reservation.getContent())
                    .createdAt(reservation.getCreateTime())
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }

    @Transactional
    public ResponseEntity<businessDto> updateBusiness(businessForm form, String buId, String uid) {
        Business business = businessRepository.findBusinessByBusiness_id(buId).orElseThrow(EntityNotFoundException::new);
        Owner owner = ownerRepository.findById(uid).orElseThrow(EntityNotFoundException::new);
        List<Reservation> reservations = reservationRepository.findReservationsByBusiness_uid(buId);


        Business updatedBusiness = Business.builder()
                .uid(business.getUid())
                .name(form.getName())
                .address(form.getAddress())
                .bu_hour(form.getBu_hour())
                .owner(owner)
                .build();

        // update 문제 있음
        reservations.forEach(reservation -> {
            reservation.setBu_name(updatedBusiness.getName());
            reservation.setBusiness(updatedBusiness);
        });


        Business savedBusiness = businessRepository.save(updatedBusiness);


        businessDto businessDto = entityToDto(savedBusiness);

        return ResponseEntity.ok(businessDto);
    }

    private businessDto entityToDto(Business business) {
        return businessDto.builder()
                .id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .build();
    }
}