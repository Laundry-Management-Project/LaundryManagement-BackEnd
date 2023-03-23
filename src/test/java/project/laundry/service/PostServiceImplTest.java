package project.laundry.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.laundry.dto.post_dto.postDto;

@SpringBootTest
@Slf4j
class PostServiceImplTest {

    @Autowired PostService service;

    @Test
    public void PostServiceTest() {
        // given
        postDto dto = postDto.builder()
                .name("홍길동")
                .phone("010-1234-5678")
                .clothCount(3)
                .price(13000)
                .build();
        // when
        String id = service.register(dto);
        // then
        log.info("id = {}", id);
    }
}