package file.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import file.service.entity.UserEntity;

import java.security.Key;
import java.util.Date;

public class JwtUtil
{
//    private static final SecretKey KEY = Keys.hmacShaKeyFor("super-secret-super-secret-super-secret".getBytes()); // at least 256 bits
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Use a strong, random key in production
    private static final long EXPIRATION_TIME = 864_000_00; // 24 hours

    public static String generateToken(UserEntity userEntity)
    {
//        return Jwts.builder()
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
////                .signWith(KEY, SignatureAlgorithm.HS512)
//                .signWith(KEY)
//                .compact();

        return Jwts.builder()
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token)
    {
//        JwtParser parser = Jwts.parserBuilder()
//        JwtParser parser = Jwts.parser()
//                .setSigningKey(KEY)
//                .build();
//
//        Claims claims = parser.parseClaimsJws(token).getBody();
//        return claims.getSubject();

//        return Jwts.parser()
//                .setSigningKey(KEY)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // old
//    public static Optional<User> parseToken(String token)
//    {
//        String username = getUsernameFromToken(token);
//
//        Optional<User> user = new UserService().findByUsername(username); // Should ideally be injected or passed in
//
//        return user;
//    }

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
