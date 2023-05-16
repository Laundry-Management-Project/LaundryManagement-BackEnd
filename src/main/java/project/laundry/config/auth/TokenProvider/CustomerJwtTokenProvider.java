package project.laundry.config.auth.TokenProvider;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.laundry.config.auth.UserDetail.service.CustomerUserDetailService;
import project.laundry.data.entity.Customer_Authority;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerJwtTokenProvider implements JwtTokenProvider<Customer_Authority> {

    @Value("${jwt.customer.secret.key}")
    private String salt;

    private Key secretKey;

    // 1시간
    private final long exp = 1000L * 60 * 60;

    private final CustomerUserDetailService customerUserDetailService;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String createToken(String customer_id, List<Customer_Authority> roles) {
        Claims claims = Jwts.claims().setSubject(customer_id);
        claims.put("roles", Collections.singletonList(roles));
        claims.put("user_type", "cu");
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 권한정보 획득
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(this.getUid(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 customer_id 획득
    public String getUid(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Authorization Header를 통해 인증을 한다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        try {
            if(!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            String user_type = claims.getBody().get("user_type").toString();
            System.out.println("customer - user_type = " + user_type);
            boolean before = claims.getBody().getExpiration().before(new Date());

            return user_type.equalsIgnoreCase("cu") && !before;
        } catch (Exception e) {
            return false;
        }
    }
}
