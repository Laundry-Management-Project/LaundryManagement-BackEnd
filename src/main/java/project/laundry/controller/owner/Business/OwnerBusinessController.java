package project.laundry.controller.owner.Business;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.request.businessForm;
import project.laundry.data.response.BusinessDtoList;
import project.laundry.data.response.common.BusinessDto;
import project.laundry.service.owner.Business.OwnerBusinessService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnerBusinessController {
    private final OwnerBusinessService service;

    @ApiOperation(value = "세탁소 목록")
    @GetMapping("owner/businesses")
    public ResponseEntity<BusinessDtoList> businesses(
            @ApiParam(name = "uId", value = "사장님 고유 id", required = true) @RequestParam("uId") String uId,
            @ApiParam(name = "page", value = "현재 페이지의 숫자 (1 부터 시작)", required = true, example = "1") @RequestParam("page") Integer pageNumber,
            @ApiParam(name = "size", value = "현재 페이지에 보여줄 요소의 개수", required = true, example = "10") @RequestParam("size") Integer pageSize) {
        return service.findBusinessesByOwner_id(uId, pageNumber, pageSize);
    }

    @ApiOperation(value = "세탁소 추가")
    @PostMapping("owner/business/add")
    public ResponseEntity<BusinessDto> businessAdd(
            @ApiParam(name = "businessForm", value = "하단 businessForm 참조", required = true) @RequestBody businessForm form,
            @ApiParam(name = "uId", value = "사장님 고유 id", example = "5602eadb-6e14-48b8-9d03-663eb130959b", required = true) @RequestParam("uId") String uId) {

        return service.saveBusiness(form, uId);
    }

    @ApiOperation(value = "세탁소 세부사항")
    @GetMapping("owner/{buId}/detail")
    public ResponseEntity<BusinessDto> BusinessDetail(
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId) {
        return service.findBusinessByBusiness_id(buId);
    }

    @ApiOperation(value = "세탁소 수정")
    @PutMapping("owner/{buId}/update")
    public ResponseEntity<BusinessDto> updateBusinessDetail(
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "uId", value = "사장님 고유 id", example = "5602eadb-6e14-48b8-9d03-663eb130959b") @RequestParam("uId") String uId,
            @ApiParam(name = "form", value = "하단 businessForm 참조", required = true) @RequestBody businessForm form) {
        return service.updateBusiness(form, buId, uId);
    }

    @ApiOperation(value = "세탁소 삭제")
    @DeleteMapping("owner/{buId}/delete")
    public void deleteBusiness(
            @ApiParam(name = "buId", value = "세탁소 고유 id", required = true) @PathVariable("buId") String buId,
            @ApiParam(name = "uId", value = "사장님 고유 id", required = false) @RequestParam("uId") String uId
    ) {
        service.deleteBusiness(buId);
    }
}
