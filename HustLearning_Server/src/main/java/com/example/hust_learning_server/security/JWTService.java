package com.example.hust_learning_server.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class JWTService {

    @Value("${keycloak.credentials.secret}")
    private  String PUBLIC_KEY;



    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(PUBLIC_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getRoleFromToken(String token) {

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(PUBLIC_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Lấy ra giá trị của claim "role"
            String role = claims.get("role", String.class);

            return role;
        }

    public boolean isValidToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode("59wN1iY4iWPsgzXXNj3NYMLzMPdaEnM1x72sK_xwwAE");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            // Tạo một Signature object với thuật toán SHA256withRSA
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(key);

            // Chuyển đổi payload thành byte array
            byte[] data = token.split("\\.")[0].getBytes("UTF-8");

            // Base64 decode chữ ký
            byte[] signatureBytes = Base64.getUrlDecoder().decode(token.split("\\.")[2]);

            // Cập nhật dữ liệu vào Signature object
            signature.update(data);

            // Xác minh chữ ký
            return signature.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
