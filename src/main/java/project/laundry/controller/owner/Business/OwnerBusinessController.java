package project.laundry.controller.owner.Business;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.request.businessForm;
import project.laundry.data.response.common.BusinessDto;
import project.laundry.service.owner.Business.OwnerBusinessService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnerBusinessController {
    private final OwnerBusinessService service;

    @GetMapping("owner/businesses")
    public ResponseEntity<List<BusinessDto>> businesses(@RequestParam("uId") String uId) {
        return service.findBusinessesByOwner_id(uId);
    }

    @ApiOperation(value = "세탁소를 등록하기 위한 메소드")
    @PostMapping("owner/business/add")
    public ResponseEntity<BusinessDto> businessAdd(
            @ApiParam(name = "businessForm", value = "name, address, bu_hour, contact, intro", required = true) @RequestBody businessForm form,
            @ApiParam(name = "uid", value = "사장님 고유 uid", example = "5602eadb-6e14-48b8-9d03-663eb130959b", required = true) @RequestParam("uId") String uId) {

        return service.saveBusiness(form, uId);
    }

    @GetMapping("owner/{buId}/detail")
    public ResponseEntity<BusinessDto> BusinessDetail(@PathVariable("buId") String buId) {
        return service.findBusinessByBusiness_id(buId);
    }

    @ApiOperation(value = "세탁소 수정화면")
    @PutMapping("owner/{buId}/update")
    public ResponseEntity<BusinessDto> updateBusinessDetail(
            @ApiParam(name = "buId", value = "세탁소 고유 uid", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "uid", value = "사장님 고유 uid", example = "5602eadb-6e14-48b8-9d03-663eb130959b") @RequestParam("uId") String uId,
            @ApiParam(name = "form", value = "수정 정보 form", required = true) @RequestBody businessForm form) {
        return service.updateBusiness(form, buId, uId);
    }

    @DeleteMapping("owner/{buId}/delete")
    public void deleteBusiness(
            @ApiParam(name = "buId", value = "세탁소 고유 uid", required = true) @PathVariable("buId") String buId,
            @RequestParam("uId") String uId
    ) {
        service.deleteBusiness(buId);
    }
}
