package com.org.onclick.domain;

import java.io.Serializable;

public class TokenResponse implements Serializable {

    private String token;

    public TokenResponse() {

    }

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
