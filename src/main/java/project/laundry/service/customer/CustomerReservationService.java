package project.laundry.service.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.entity.Business;
import project.laundry.repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerReservationService {

    private final ReservationRepository reservationRepository;

    public ResponseEntity<List<businessDto>> findBusinessesByCustomerUid(String uid) {
        List<Business> businesses = reservationRepository.findByCustomerUid(uid);


        List<businessDto> businessDtos = businesses.stream().map(business -> businessDto.builder()
                .id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .build()).collect(Collectors.toList());

        return ResponseEntity.ok(businessDtos);

    }

}
