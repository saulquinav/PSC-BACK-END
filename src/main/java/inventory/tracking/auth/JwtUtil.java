package inventory.tracking.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import inventory.tracking.entity.UserEntity;

import java.security.Key;
import java.util.Date;



//public class JwtUtil
//{
////    private static final SecretKey KEY = Keys.hmacShaKeyFor("super-secret-super-secret-super-secret".getBytes()); // at least 256 bits
////    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Use a strong, random key in production
//
//    private static final String SECRET_KEY = "secret";
//
//    private static final long EXPIRATION_TIME = 24; // 24 hours
//
//    /* This method generates a JSON Web Token (JWT), for proving the user's identity */
//    public static String generateToken(String username, String role)
//    {
//        return JWT.create()
//                .withSubject(username)
//                .withClaim("role", role)
//                .withExpiresAt(Date.from(Instant.now().plus(EXPIRATION_TIME, ChronoUnit.HOURS)))
//                .sign(Algorithm.HMAC256(SECRET_KEY));
//    }
//
//    public static DecodedJWT verifyToken(String token) throws JWTVerificationException
//    {
//        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
//    }
//}

public class JwtUtil
{
    //    private static final SecretKey KEY = Keys.hmacShaKeyFor("super-secret-super-secret-super-secret".getBytes()); // at least 256 bits
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Use a strong, random key in production
    private static final long EXPIRATION_TIME = 864_000_00; // 24 hours

    public static String generateToken(UserEntity userEntity)
    {
        return Jwts.builder()
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(KEY, SignatureAlgorithm.HS512)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
