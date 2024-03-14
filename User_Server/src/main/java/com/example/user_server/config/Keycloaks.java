package com.example.user_server.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Keycloaks {
    @Value("${keycloak.realm}")
    private  String realm;
    @Value("${keycloak.auth-server-url}")
    private  String serverUrl;

    @Value("${keycloak.username}")
    private  String username;
    @Value("${keycloak.password}")
    private  String password;
    @Value("${keycloak.resource}")
    private  String clientId;

    @Value("${keycloak.credentials.secret}")
    private  String clientSecret;
    public  Keycloak getKeycloakInstance() {
        return Keycloak.getInstance(
                serverUrl,
                realm,
                username,
                password,
                clientId,
                clientSecret);
    }
}
