package project.laundry.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.dto.status.ownerResponseStatus;
import project.laundry.domain.entity.Business;
import project.laundry.domain.entity.Owner;
import project.laundry.domain.form.loginForm;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerLoginService {
    private final OwnerRepository ownerRepository;

    public ResponseEntity<ownerResponseStatus> authenticateOwnerLogin(loginForm form) {

        Owner owner = ownerRepository.findByOwner_idAndPassword(form.getId(), form.getPassword());

        ownerResponseStatus ownerResponseStatus = CreateOwnerLoginStatus(owner);

        return ResponseEntity.ok(ownerResponseStatus);
    }

    private ownerResponseStatus CreateOwnerLoginStatus(Owner owner) {

        if(owner == null) {
            ownerResponseStatus rs = new ownerResponseStatus("가입되지 않은 사용자 입니다.", false, null);
            rs.setBusinessList(null);

            return rs;
        }

        List<Business> businesses = owner.getBusinesses();

        List<businessDto> businessDtos = businesses.stream().map(business -> businessDto.builder()
                .id(business.getUid())
                .name(business.getName())
                .address(business.getAddress())
                .bu_hour(business.getBu_hour())
                .build()).collect(Collectors.toList());

        ownerResponseStatus rs = new ownerResponseStatus("로그인이 완료되었습니다.", true, owner.getId());
        rs.setBusinessList(businessDtos);

        return rs;
    }
}
