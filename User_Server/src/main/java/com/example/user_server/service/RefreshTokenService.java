package com.example.user_server.service;


import org.keycloak.representations.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken create(Long id);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
}
