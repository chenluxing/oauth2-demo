package com.lxc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * JWT DEMO
 */
public class JwtDemo {

    private static final String key_secret = "123456";

    /**
     * 生成JWT
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     */
    private String createJWT(String id, String issuer, String subject, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key_secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解析和验证token码
     * @param jwt
     */
    public Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key_secret))
            .parseClaimsJws(jwt).getBody();
        System.out.println("ID：" + claims.getId());
        System.out.println("Subject：" + claims.getSubject());
        System.out.println("Issuer：" + claims.getIssuer());
        System.out.println("Expration：" + claims.getExpiration());
        return claims;
    }

    public static void main(String[] args) {
        JwtDemo demo = new JwtDemo();
        String id = "id_123456";
        String issuer = "iss_admin";
        String subject = "sub_hello_jwt";
        long ttlMillis = 100000;
        String jwtStr = demo.createJWT(id, issuer, subject, ttlMillis);
        System.out.println("JWT:" + jwtStr);

        demo.parseJWT(jwtStr);


    }

}
