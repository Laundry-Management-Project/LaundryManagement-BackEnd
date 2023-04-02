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
import project.laundry.domain.form.loginForm;
import project.laundry.exception.FormNullPointerException;
import project.laundry.exception.UserNullPointerException;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerLoginService {
    private final OwnerRepository ownerRepository;

    public ResponseEntity<ownerResponseStatus> authenticateOwnerLogin(loginForm form) {

        try {

            if(form == null || Stream.of(form.getId(), form.getPassword()).anyMatch(Objects::isNull)) {
                throw new FormNullPointerException();
            }

            Owner owner = ownerRepository.findByOwner_idAndPassword(form.getId(), form.getPassword());

            ownerResponseStatus ownerResponseStatus = CreateOwnerLoginStatus(owner);

            return ResponseEntity.ok(ownerResponseStatus);
        } catch (FormNullPointerException e) {
            ownerResponseStatus rs = new ownerResponseStatus("올바르지 않은 양식입니다.", false, null);
            rs.setBusinessList(null);

            return ResponseEntity.badRequest().body(rs);
        } catch (UserNullPointerException e) {
            ownerResponseStatus rs = new ownerResponseStatus("가입되지 않은 사용자 입니다.", false, null);
            rs.setBusinessList(null);

            return ResponseEntity.badRequest().body(rs);
        }
    }

    private ownerResponseStatus CreateOwnerLoginStatus(Owner owner) throws UserNullPointerException {

        if(owner == null) {
           throw new UserNullPointerException();
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
