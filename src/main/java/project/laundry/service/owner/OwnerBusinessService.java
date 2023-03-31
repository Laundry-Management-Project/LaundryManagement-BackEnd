package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.entity.Business;
import project.laundry.domain.entity.Owner;
import project.laundry.domain.form.businessForm;
import project.laundry.repository.BusinessRepository;
import project.laundry.repository.OwnerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerBusinessService {

    private final BusinessRepository businessRepository;

    private final OwnerRepository ownerRepository;

    @Transactional
    public ResponseEntity<businessDto> saveBusiness(businessForm form, String owner_uid) {

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

        return ResponseEntity.internalServerError().body(null);
    }

    public ResponseEntity<businessDto> findBusiness(String uid) {
        Optional<Business> businessOptional = businessRepository.findById(uid);

        if(businessOptional.isPresent()) {
            Business business = businessOptional.get();

            businessDto dto = businessDto.builder()
                    .id(business.getUid())
                    .name(business.getName())
                    .address(business.getAddress())
                    .bu_hour(business.getBu_hour())
                    .build();

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.badRequest().body(null);
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
