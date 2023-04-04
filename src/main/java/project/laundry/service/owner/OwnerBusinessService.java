package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Owner;
import project.laundry.data.entity.Reservation;
import project.laundry.data.form.businessForm;
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
public class OwnerBusinessService {

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
        List<Reservation> reservations = reservationRepository.findReservationsByBusiness_id(business_id);


        List<reservationDto> dto = reservations.stream().map(reservation -> {
            Business business = reservation.getBusiness();
            return reservationDto.builder()
                    .id(reservation.getId())
                    .cu_name(reservation.getCu_name())
                    .bu_name(business.getName())
                    .bu_address(business.getAddress())
                    .clothCount(reservation.getClothCount())
                    .clothStatus(reservation.getClothStatus().getStatus())
                    .content(reservation.getContent())
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dto);

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
