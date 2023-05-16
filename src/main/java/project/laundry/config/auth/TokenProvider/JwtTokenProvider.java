package project.laundry.config.auth.TokenProvider;

import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface JwtTokenProvider<T> {

    String createToken(String customer_id, List<T> roles);

    Authentication getAuthentication(String token);

    String getUid(String token);

    String resolveToken(HttpServletRequest request);

    boolean validateToken(String token);

}
