package com.jwt.demo.controller;


import com.jwt.demo.dto.JwtResponse;
import com.jwt.demo.util.JwtUtil;
import com.jwt.demo.exception.InvalidArgumentException;
import com.jwt.demo.exception.UnAuthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final JwtUtil jwtUtil;
    AuthController(JwtUtil jwtUtil){
      this.jwtUtil = jwtUtil;
    }
    @GetMapping("/auth")
    public ResponseEntity<?> login(String user) {
        JwtResponse response = jwtUtil.generateToken(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> profile(String user,String token) {
        if(user.isEmpty()||token.isEmpty()){
            throw new InvalidArgumentException("user or token is null/empty");
        }
        JwtResponse response = jwtUtil.validateToken(token);
        if (response.getUser().equalsIgnoreCase(user)) {
            return ResponseEntity.ok(response);
        }else {
            throw new UnAuthorizedException("Invalid user or token");
        }
    }
}
