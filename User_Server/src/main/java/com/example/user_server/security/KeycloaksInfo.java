package com.example.user_server.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeycloaksInfo {
    private Long exp;
    private Long iat;
    private String jti;
    private String iss;
    private String aud;
    private String sub;
    private String typ;
    private String azp;
    private String session_state;
    private String acr;
    @JsonProperty("allowed-origins")
    private List<String> allowed_origins;
    private RealmAccess realm_access;
    private ResourceAccess resource_access;
    private String scope;
    private String sid;
    private Boolean email_verified;
    private String preferred_username;
    private String email;
    private String given_name;
    private String family_name;

    // Getters and setters

    @JsonProperty("exp")
    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    @JsonProperty("iat")
    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    @JsonProperty("jti")
    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    // Các getter và setter khác

    @JsonProperty("realm_access")
    public RealmAccess getRealmAccess() {
        return realm_access;
    }
    @JsonSetter("realm_access")
    public void setRealmAccess(RealmAccess realm_access) {
        this.realm_access = realm_access;
    }

    @JsonProperty("resource_access")
    public ResourceAccess getResourceAccess() {
        return resource_access;
    }
    @JsonSetter("resource_access")
    public void setResourceAccess(ResourceAccess resource_access) {
        this.resource_access = resource_access;
    }
    public static class RealmAccess {
        private List<String> roles;

        // Getters and setters

        @JsonProperty("roles")
        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
    public static class ResourceAccess {
        private Account account;

        // Getters and setters

        @JsonProperty("account")
        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
    }

    public static  class Account {
        private List<String> roles;

        // Getters and setters

        @JsonProperty("roles")
        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}




