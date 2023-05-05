package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.response.common.BusinessDto;
import project.laundry.data.response.BusinessDtoList;
import project.laundry.data.entity.Business;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.repository.BusinessRepository;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerBusinessService {

    private final BusinessRepository businessRepository;

    public ResponseEntity<BusinessDtoList> findBusinesses() {
        List<Business> businesses = businessRepository.findAll();

        List<BusinessDto> businessDtoList = businesses.stream().map(business -> BusinessDto.builder()
                .bu_id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .contact(business.getContact())
                .intro(business.getIntro())
                .build()).collect(Collectors.toList());

        BusinessDtoList build = BusinessDtoList.builder().businesses(businessDtoList).build();

        return ResponseEntity.ok(build);
    }

    public ResponseEntity<BusinessDto> findBusiness(String bu_id) {
        Business business = businessRepository.findBusinessByBusiness_id(bu_id).orElseThrow(EntityNotFoundException::new);

        BusinessDto dto = BusinessDto.builder()
                .bu_id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getAddress())
                .contact(business.getContact())
                .intro(business.getIntro())
                .build();

        return ResponseEntity.ok(dto);

    }
}
