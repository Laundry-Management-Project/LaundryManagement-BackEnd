package project.laundry.config.auth.Filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import project.laundry.config.auth.TokenProvider.OwnerJwtTokenProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class OwnerJwtAuthenticationFilter extends OncePerRequestFilter {

    private final OwnerJwtTokenProvider ownerJwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        if(token != null) {
            if(ownerJwtTokenProvider.validateToken(token)) {
                token = token.split(" ")[1].trim();
                Authentication auth = ownerJwtTokenProvider.getAuthentication(token);
                System.out.println("auth = " + auth);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }


        chain.doFilter(request, response);
    }
}
