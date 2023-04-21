package project.laundry.service.common.Login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import project.laundry.data.response.common.LoginDto;
import project.laundry.data.request.loginForm;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginServiceTest {

    @Autowired LoginService service;

    @Test
    public void loginTes() {
        // given
        loginForm form = new loginForm();
        form.setUser_type("cu");
        form.setId("test");
        form.setPassword("1234");

        // when
        ResponseEntity<LoginDto> response = service.authenticateLogin(form);

        // then
        assertThat(response.getBody().getId()).isEqualTo(form.getId());
    }

}