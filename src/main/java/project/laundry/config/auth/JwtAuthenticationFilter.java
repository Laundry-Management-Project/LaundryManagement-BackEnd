package project.laundry.config.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomerJwtTokenProvider customerJwtTokenProvider;
    private final OwnerJwtTokenProvider ownerJwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        if(token != null) {
            if(customerJwtTokenProvider.validateToken(token)) {
                token = token.split(" ")[1].trim();
                Authentication auth = customerJwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else if(ownerJwtTokenProvider.validateToken(token)) {
                token = token.split(" ")[1].trim();
                Authentication auth = ownerJwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }


        chain.doFilter(request, response);
    }
}
