package com.jwt.demo.util;


import com.jwt.demo.dto.JwtResponse;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SUBSCRIPTION_KEY = "secret123";

    public JwtResponse generateToken(String username) {
        Date createdOn = new Date(System.currentTimeMillis());
        Date expired = new Date(System.currentTimeMillis() + 100000);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(createdOn)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS256, SUBSCRIPTION_KEY)
                .compact();
        return new JwtResponse(token,"Just Created",username,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdOn),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expired));
    }
    public JwtResponse validateToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SUBSCRIPTION_KEY)
                .parseClaimsJws(token)
                .getBody();

        return new JwtResponse(token,"Active",claims.getSubject(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration())
        );
    }

}
