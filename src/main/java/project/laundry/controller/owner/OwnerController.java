package project.laundry.controller.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.common.reservationDto;
import project.laundry.data.form.businessForm;
import project.laundry.service.owner.OwnerBusinessService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerBusinessService service;

    @PostMapping("/{uid}/businesses/add")
    public ResponseEntity<businessDto> businessAddPage(@RequestBody businessForm form, @PathVariable("uid") String owner_uid) {

        return service.saveBusiness(form, owner_uid);
    }

    @GetMapping("/{uid}/businesses/{buId}/home")
    public ResponseEntity<List<reservationDto>> businessDetailPage(@PathVariable("buId") String buId, @PathVariable("uid") String uid) {
        return service.findReservationsByBusiness_id(buId);
    }
}
