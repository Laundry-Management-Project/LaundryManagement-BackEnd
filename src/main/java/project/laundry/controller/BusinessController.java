package project.laundry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.laundry.domain.dto.businessDto;
import project.laundry.domain.form.businessForm;
import project.laundry.service.owner.OwnerBusinessService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
public class BusinessController {
    private final OwnerBusinessService service;

    @GetMapping("/{uid}/businesses")
    public ResponseEntity<String> BusinessesPage(@PathVariable("uid") String uid) {
        return ResponseEntity.ok(uid);
    }


    @PostMapping("/{uid}/businesses/add")
    public ResponseEntity<businessDto> businessAddPage(@RequestBody businessForm form, @PathVariable("uid") String owner_uid) {

        return service.saveBusiness(form, owner_uid);
    }

    @GetMapping("/{uid}/businesses/{buId}/home")
    public void businessDetailPage(@PathVariable("uid") String uid, @PathVariable("buId") String buId) {

    }
}
