package dev.benew.infomining_api_example_java.dto;


import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
public class AuthTokenResponse implements Serializable {
    private String refresh_token;
    private LocalDateTime refresh_expired;
    private String access_token;
    private LocalDateTime access_expired;

    public AuthTokenResponse() {}
    public AuthTokenResponse(String refresh_token, LocalDateTime refresh_expired, String access_token, LocalDateTime access_expired) {
        this.refresh_token = refresh_token;
        this.refresh_expired = refresh_expired;
        this.access_token = access_token;
        this.access_expired = access_expired;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public LocalDateTime getRefresh_expired() {
        return refresh_expired;
    }

    public void setRefresh_expired(LocalDateTime refresh_expired) {
        this.refresh_expired = refresh_expired;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public LocalDateTime getAccess_expired() {
        return access_expired;
    }

    public void setAccess_expired(LocalDateTime access_expired) {
        this.access_expired = access_expired;
    }
}
