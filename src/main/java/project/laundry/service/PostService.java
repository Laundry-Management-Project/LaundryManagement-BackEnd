package project.laundry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.dto.post.postDto;
import project.laundry.entity.Post;
import project.laundry.entity.Visit;
import project.laundry.repository.PostRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository repository;

    @Transactional
    public String register(postDto dto) {
        Post entity = dtoToEntity(dto);

        Visit visit = Visit.of(entity, entity.getPrice());
        log.info("visit.getPrice : {}", visit.getPrice());
        entity.addVisit(visit);
        repository.save(entity);

        return entity.getId();
    }


    public postDto findOne(String postId) {
        Optional<Post> findPost = repository.findById(postId);
        return findPost.map(this::entityToDto).orElse(null);
    }


    public List<postDto> findAll() {
        List<Post> Posts = repository.findAll();

        return Posts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public String update(String postId, postDto dto) {

        Post findPost = repository.findById(postId).orElseThrow(() -> new RuntimeException("존재하지 않는 손님입니다."));

        List<Visit> visits = findPost.getVisits();

        visits.forEach(v -> {
            v.setPrice(dto.getPrice());
            v.setVisitDateTime(LocalDate.now());
            v.setPost(findPost);
        });


        return findPost.updatePost(dto);
    }

    @Transactional
    public void delete(String postId) {

        Post findPost = repository.findById(postId).orElseThrow(() -> new RuntimeException("존재하지 않는 손님입니다."));
        repository.delete(findPost);

    }


    public List<postDto> findTop5ByOrderByIdDesc() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
        Page<Post> top5Posts = repository.findTop5ByOrderByIdDesc(pageable);

        List<Post> posts = top5Posts.getContent();

        log.info("posts : {}", posts);

        return posts.stream().limit(5)
                .map(this::entityToDto).collect(Collectors.toList());
    }


    public Post dtoToEntity(postDto dto) {
        return Post.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .clothCount(dto.getClothCount())
                .clothStatus(dto.getClothStatus())
                .price(dto.getPrice())
                .content(dto.getContent())
                .build();
    }

    public postDto entityToDto(Post entity) {
        return postDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .clothCount(entity.getClothCount())
                .clothStatus(entity.getClothStatus())
                .price(entity.getPrice())
                .content(entity.getContent())
                .createTime(entity.getCreateTime())
                .build();
    }
}
