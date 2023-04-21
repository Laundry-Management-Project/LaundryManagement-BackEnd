package project.laundry.service.owner.Business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Owner;
import project.laundry.data.entity.Reservation;
import project.laundry.data.request.businessForm;
import project.laundry.data.response.common.BusinessDto;
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
public class OwnerBusinessService {

    private final BusinessRepository businessRepository;
    private final OwnerRepository ownerRepository;
    private final ReservationRepository reservationRepository;

    public ResponseEntity<BusinessDto> findBusinessByBusiness_id(String buId) {
        Business business = businessRepository.findBusinessByBusiness_id(buId).orElseThrow(EntityNotFoundException::new);

        BusinessDto dto = BusinessDto.builder()
                .bu_id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .contact(business.getContact())
                .intro(business.getIntro())
                .build();

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<BusinessDto>> findBusinessesByOwner_id(String uId) {
        List<Business> businesses = businessRepository.findBusinessesByOwner_id(uId);
        List<BusinessDto> collect = businesses.stream().map(this::entityToDto).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }


    @Transactional
    public ResponseEntity<BusinessDto> saveBusiness(businessForm form, String owner_uid) {

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
                        .contact(form.getContact())
                        .intro(form.getIntro())
                        .owner(owner)
                        .build();
                businessRepository.save(business);

                BusinessDto businessDto = entityToDto(business);

                return ResponseEntity.ok(businessDto);
            }
        } catch (FormNullPointerException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (UserNullPointerException e) {
            return ResponseEntity.internalServerError().body(null);
        }

        return ResponseEntity.internalServerError().body(null);
    }

    @Transactional
    public ResponseEntity<BusinessDto> updateBusiness(businessForm form, String buId, String uid) {
        Business business = businessRepository.findBusinessByBusiness_id(buId).orElseThrow(EntityNotFoundException::new);
        Owner owner = ownerRepository.findById(uid).orElseThrow(EntityNotFoundException::new);
        List<Reservation> reservations = reservationRepository.findReservationsByBusiness_uid(buId);


        Business updatedBusiness = Business.builder()
                .uid(business.getUid())
                .name(form.getName())
                .address(form.getAddress())
                .bu_hour(form.getBu_hour())
                .contact(form.getContact())
                .intro(form.getIntro())
                .owner(owner)
                .build();

        // update 문제 있음 - N + 1
        reservations.forEach(reservation -> reservation.setBusiness(updatedBusiness));


        Business savedBusiness = businessRepository.save(updatedBusiness);


        BusinessDto businessDto = entityToDto(savedBusiness);

        return ResponseEntity.ok(businessDto);
    }

    public void deleteBusiness(String buId) {
        Business business = businessRepository.findBusinessByBusiness_id(buId).orElseThrow(EntityNotFoundException::new);
        businessRepository.delete(business);
    }

    private BusinessDto entityToDto(Business business) {
        return BusinessDto.builder()
                .bu_id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .contact(business.getContact())
                .intro(business.getIntro())
                .build();
    }
}
