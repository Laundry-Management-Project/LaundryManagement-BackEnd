package project.laundry.service.owner.Business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Owner;
import project.laundry.data.entity.Reservation;
import project.laundry.data.request.businessForm;
import project.laundry.data.response.BusinessDtoList;
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

    public ResponseEntity<BusinessDtoList> findBusinessesByOwner_id(String uId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("name").ascending());
        Page<Business> pages = businessRepository.findBusinessesByOwner_id(uId, pageable);
        List<Business> businesses = pages.getContent();
        int totalPages = pages.getTotalPages();
        long totalElements = pages.getTotalElements();

        List<BusinessDto> collect = businesses.stream().map(this::entityToDto).collect(Collectors.toList());

        BusinessDtoList build = BusinessDtoList.builder()
                .businesses(collect)
                .totalPages(totalPages)
                .totalItems(totalElements)
                .build();
        return ResponseEntity.ok(build);
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

        business.setName(form.getName());
        business.setAddress(form.getAddress());
        business.setBu_hour(form.getBu_hour());
        business.setContact(form.getContact());
        business.setIntro(form.getIntro());

        Business savedBusiness = businessRepository.save(business);


        BusinessDto businessDto = entityToDto(savedBusiness);

        return ResponseEntity.ok(businessDto);
    }

    @Transactional
    public void deleteBusiness(String buId) {
        List<Reservation> reservations = reservationRepository.findReservationsByBusinessUid(buId);
        reservationRepository.deleteReservationsWithInQuery(reservations);
        businessRepository.deleteById(buId);
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
