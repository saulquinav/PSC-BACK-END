package inventory.tracking.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.security.Key;


public class JwtUtil
{
//    private static final SecretKey KEY = Keys.hmacShaKeyFor("super-secret-super-secret-super-secret".getBytes()); // at least 256 bits
//    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Use a strong, random key in production

    private static final String SECRET_KEY = "secret";

    private static final long EXPIRATION_TIME = 24; // 24 hours

    /* This method generates a JSON Web Token (JWT), for proving the user's identity */
    public static String generateToken(String username, String role)
    {
        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withExpiresAt(Date.from(Instant.now().plus(EXPIRATION_TIME, ChronoUnit.HOURS)))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static DecodedJWT verifyToken(String token) throws JWTVerificationException
    {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
    }
}
