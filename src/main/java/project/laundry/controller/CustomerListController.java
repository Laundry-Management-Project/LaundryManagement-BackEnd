package project.laundry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.laundry.dto.post.postDto;
import project.laundry.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer-list")
@RequiredArgsConstructor
@Slf4j
public class CustomerListController {

    private final PostService service;


    @GetMapping
    public ResponseEntity<List<postDto>> ListPage(Model model) {
        List<postDto> posts = service.findAll();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/add")
    public ResponseEntity<String> GET_AddPage(Model model) {

        return ResponseEntity.ok("Request OK");
    }

    // 응답 상태 변경 - ResponseStatus -
    @PostMapping("/add")
    public ResponseEntity<String> POST_AddPage(@RequestBody @Valid postDto postDto, BindingResult br, RedirectAttributes redirectAttributes, Model model) {

        if(br.hasErrors()) {
            log.error("POST_AddPage BR : {}", br);
            return ResponseEntity.badRequest().body(br.toString());
        }

        String postId = service.register(postDto);

        return ResponseEntity.ok("OK");
    }


    @GetMapping("/{postId}/detail")
    public ResponseEntity<postDto> GET_detail(@PathVariable String postId, Model model) {
        postDto postDto = service.findOne(postId);

        return ResponseEntity.ok(postDto);
    }


    @GetMapping("/{postId}/detail/update")
    public ResponseEntity<postDto> GET_update(@PathVariable String postId, Model model) {
        postDto postDto = service.findOne(postId);

        return ResponseEntity.ok(postDto);
    }


    @PostMapping("/{postId}/detail/update")
    public ResponseEntity<String> POST_update(@RequestBody @Valid postDto dto, BindingResult br, @PathVariable String postId) {

        if(br.hasErrors()) {
            log.error("POST_update BR : {}", br);
            return ResponseEntity.badRequest().body(null);
        }
        String updatePostId = service.update(postId, dto);

        return ResponseEntity.ok(updatePostId);
    }


    @GetMapping("/{postId}/detail/delete")
    public ResponseEntity<String> DELETE_Post(@PathVariable("postId") String postId, Model model) {
        service.delete(postId);

        return ResponseEntity.ok("DELETE OK");
    }



}
