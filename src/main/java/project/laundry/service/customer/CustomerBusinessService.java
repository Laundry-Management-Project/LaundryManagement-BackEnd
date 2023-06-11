package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.response.common.BusinessDto;
import project.laundry.data.response.BusinessDtoList;
import project.laundry.data.entity.Business;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.repository.BusinessRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomerBusinessService {

    private final BusinessRepository businessRepository;

    public ResponseEntity<BusinessDtoList> findBusinesses(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        Page<Business> pages = businessRepository.findAll(pageRequest);

        List<Business> businesses = pages.getContent();
        int totalPages = pages.getTotalPages();
        long totalElements = pages.getTotalElements();

        List<BusinessDto> businessDtoList = businesses.stream().map(business -> BusinessDto.builder()
                .bu_id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .contact(business.getContact())
                .intro(business.getIntro())
                .build()).collect(Collectors.toList());

        BusinessDtoList build = BusinessDtoList
                .builder()
                .businesses(businessDtoList)
                .totalPages(totalPages)
                .totalItems(totalElements)
                .build();

        return ResponseEntity.ok(build);
    }

    public ResponseEntity<BusinessDto> findBusiness(String bu_id) {
        Business business = businessRepository.findBusinessByBusiness_id(bu_id).orElseThrow(EntityNotFoundException::new);

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
}
