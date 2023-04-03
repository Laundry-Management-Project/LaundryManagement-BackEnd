package project.laundry.controller.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.dto.customerListDto;
import project.laundry.domain.form.businessForm;
import project.laundry.service.owner.OwnerBusinessService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerBusinessService service;

    // 사용 X
    @ApiIgnore
    @GetMapping("/{uid}/businesses")
    public ResponseEntity<List<businessDto>> BusinessesPage(@PathVariable("uid") String uid) {
        return service.findBusinessesByOwner_id(uid);
    }


    @PostMapping("/{uid}/businesses/add")
    public ResponseEntity<businessDto> businessAddPage(@RequestBody businessForm form, @PathVariable("uid") String owner_uid) {

        return service.saveBusiness(form, owner_uid);
    }

    @GetMapping("/{uid}/businesses/{buId}/home")
    public ResponseEntity<List<customerListDto>> businessDetailPage(@PathVariable("uid") String uid, @PathVariable("buId") String buId) {
        return service.findCustomerListByBusiness_id(buId);
    }
}
