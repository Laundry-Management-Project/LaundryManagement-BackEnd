package project.laundry.controller.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import project.laundry.service.common.Signup.OwnerSignUpService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebMvcTest
class OwnerSignUpControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean
    private OwnerSignUpService service;



}