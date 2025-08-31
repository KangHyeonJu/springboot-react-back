package com.codehows.cardatabase.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.security.Key;

@Component
public class JWTService {
    //토큰 만료 시간(밀리초) - 1일(86,400,000ms). 실제 운영 시에는 더 짧아야 함
    static final long EXPIRATIONTIME = 86400000;
    static final String PREFIX = "Bearer";

    //비밀키 생성. 시연 목적으로만 이용
    //운영 환경에서는 애플리케이션 구성에서 읽어들여와야 함
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //서명된 JWT 토큰 생성
    public String getToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).signWith(key).compact();
        return token;
    }

    //요청의 Authorization 헤더에서 토큰을 가져온 뒤 토큰을 확인하고 사용자 이름을 가져옴
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody().getSubject();
            if (user != null)
                return user;
        }
        return null;
    }
}
