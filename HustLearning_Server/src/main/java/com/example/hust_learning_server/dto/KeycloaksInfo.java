package com.example.hust_learning_server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeycloaksInfo {
    private long exp;
    private long iat;
    private String jti;
    private String iss;
    private String aud;
    private String sub;
    private String typ;
    private String azp;
    private String session_state;
    private String acr;
    @JsonIgnore
    private RealmAccess realm_access;
    @JsonIgnore
    private ResourceAccess resource_access;
    private String scope;
    private String sid;
    private boolean email_verified;
    private String preferred_username;
    private String email;
    private  List<String> allowedOrigins;
    @JsonProperty("allowed-origins")
    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    @JsonProperty("allowed-origins")
    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }


    static class RealmAccess {
        private List<String> roles;

    }

    static class ResourceAccess {
        private Account account;
    }


    static class Account {
        private List<String> roles;
    }
}
