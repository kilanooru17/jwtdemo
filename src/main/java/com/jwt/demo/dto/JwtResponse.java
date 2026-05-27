package com.jwt.demo.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String user;
    private String createdOn;
    private String token;
    private String expire;
    private String status;
    public JwtResponse(String token,
                       String status,
                       String user,
                       String createdOn,
                       String expire) {
        this.status = status;
        this.user = user;
        this.createdOn = createdOn;
        this.expire = expire;
        this.token = token ;
    }
}
