package project.laundry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티필터가 스프링 필터 체인에 등록됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해줌
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//            .antMatchers("/owner/**").authenticated()  // 해당 경로는 인증이 필요함
//            .antMatchers("/customer/**").authenticated() // 해당 경로는 인증이 필요함
//            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // ROLE이 ROLE_ADMIN일 경우에만 허용함
//            .anyRequest().permitAll();
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .loginProcessingUrl("/login") // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행함
//            .defaultSuccessUrl("/");
    }
}
