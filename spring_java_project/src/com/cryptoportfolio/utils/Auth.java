package com.cryptoportfolio.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cryptoportfolio.exceptions.AuthenticationException;

public class Auth {
    public static void authenticateToken(String username, String token) {
        if (null == username || "".equals(username) || null == token || "".equals(token)) {
            throw new AuthenticationException("[Unauthorized] Authentication Failed : username and token required");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("cryptoportfolio")
                    .withClaim("username", username)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (TokenExpiredException e) {
            throw new AuthenticationException("Authentication Failed : token expired");
        } catch (JWTVerificationException e) {
            throw new AuthenticationException("Authentication Failed : token could not be verified");
        }
    }
}
