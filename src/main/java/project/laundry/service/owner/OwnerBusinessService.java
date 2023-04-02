package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.entity.Business;
import project.laundry.domain.entity.Owner;
import project.laundry.domain.form.businessForm;
import project.laundry.exception.FormNullPointerException;
import project.laundry.exception.UserNullPointerException;
import project.laundry.repository.BusinessRepository;
import project.laundry.repository.OwnerRepository;

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

    public ResponseEntity<List<businessDto>> findBusinessesByOwner_id(String uid) {

        List<Business> businesses = businessRepository.findBusinessesByOwner_id(uid);


        List<businessDto> collect = businesses.stream().map(business -> businessDto.builder()
                .id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .build()).collect(Collectors.toList());


        return ResponseEntity.ok(collect);
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
