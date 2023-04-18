package project.laundry.controller.owner;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.form.businessForm;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.service.owner.OwnerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService service;

    @ApiOperation(value = "사업장(세탁소)를 등록하기 위한 메소드")
    @PostMapping("owner/{uid}/business/add")
    public ResponseEntity<businessDto> businessAdd(
            @ApiParam(name = "businessForm", value = "name, address, bu_hour", required = true) @RequestBody businessForm form,
            @ApiParam(name = "uid", value = "사장님 고유 uid", example = "5602eadb-6e14-48b8-9d03-663eb130959b", required = true) @PathVariable("uid") String owner_uid) {

        return service.saveBusiness(form, owner_uid);
    }

    @ApiOperation(value = "사업장 상세화면")
    @GetMapping("owner/{uid}/business/{buId}/detail")
    public ResponseEntity<List<reservationDto>> businessDetail(
            @ApiParam(name = "buId", value = "사업장 고유 uid", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "uid", value = "사장님 고유 uid", example = "5602eadb-6e14-48b8-9d03-663eb130959b") @PathVariable("uid") String uid) {
        return service.findReservationsByBusiness_id(buId);
    }


    @ApiOperation(value = "사업장 수정화면")
    @PutMapping("owner/{uid}/business/{buId}/detail")
    public ResponseEntity<businessDto> updateBusinessDetail(
            @ApiParam(name = "form", value = "수정 정보 form", required = true) @RequestBody businessForm form,
            @ApiParam(name = "buId", value = "사업장 고유 uid", required = true) @PathVariable String buId,
            @ApiParam(name = "uid", value = "사장님 고유 uid", example = "5602eadb-6e14-48b8-9d03-663eb130959b") @PathVariable String uid) {
        return service.updateBusiness(form, buId, uid);
    }

}
