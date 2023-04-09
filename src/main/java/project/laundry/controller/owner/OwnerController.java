package project.laundry.controller.owner;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.form.businessForm;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.service.owner.OwnerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService service;

    @ApiOperation(value = "사업장(세탁소)를 등록하기 위한 메소드")
    @PostMapping("/{uid}/business/add")
    public ResponseEntity<businessDto> businessAddPage(@ApiParam(name = "businessForm", value = "name, address, bu_hour", required = true) @RequestBody businessForm form,
                                                       @ApiParam(name = "uid", value = "사장님 고유 uid", required = true) @PathVariable("uid") String owner_uid) {

        return service.saveBusiness(form, owner_uid);
    }

    @ApiOperation(value = "사업장 상세화면")
    @GetMapping("/{uid}/business/detail/{buId}")
    public ResponseEntity<List<reservationDto>> businessDetailPage(
            @ApiParam(name = "buId", value = "사업장 고유 uid", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "uid", value = "사장님 고유 uid") @PathVariable("uid") String uid) {
        return service.findReservationsByBusiness_id(buId);
    }


    @ApiOperation(value = "사업장 수정화면")
    @PutMapping("/{uid}/business/detail/{buId}")
    public ResponseEntity<businessDto> updateBusinessDetail(
            @ApiParam(name = "form", value = "수정 정보 form", required = true) @RequestBody businessForm form,
            @ApiParam(name = "buId", value = "사업장 고유 uid", required = true) @PathVariable String buId,
            @ApiParam(name = "uid", value = "사장님 고유 uid") @PathVariable String uid) {
        return service.updateBusiness(form, buId, uid);
    }
}
