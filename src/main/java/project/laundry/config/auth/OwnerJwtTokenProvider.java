package project.laundry.config.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.laundry.config.auth.UserDetail.service.OwnerUserDetailService;
import project.laundry.data.entity.Owner_Authority;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerJwtTokenProvider {

    @Value("${jwt.owner.secret.key}")
    private String salt;

    private Key secretKey;

    // 1시간
    private final long exp = 1000L * 60 * 60;

    private final OwnerUserDetailService ownerUserDetailService;

    @PostConstruct
    protected  void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String owner_id, List<Owner_Authority> roles) {
        Claims claims = Jwts.claims().setSubject(owner_id);
        claims.put("roles", roles);
        claims.put("user_type", "ow");
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
        UserDetails userDetails = ownerUserDetailService.loadUserByUsername(this.getOwner_id(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 customer_id 획득
    public String getOwner_id(String token) {
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
            boolean before = claims.getBody().getExpiration().before(new Date());

            return user_type.equalsIgnoreCase("ow") && !before;

        } catch (Exception e) {
            return false;
        }
    }

}
