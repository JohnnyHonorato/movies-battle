package lets.code.moviesbattle.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lets.code.moviesbattle.model.User;
import lets.code.moviesbattle.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticateService {

    public static final long EXPIRATION_TIME = 860_000_000;

    public static final String SECRET = "SECRET_KEY";

    public static final String TOKEN_PREFIX = "Bearer ";

    private PasswordEncoder passwordEncoder;

    private UserService userService;

    public AuthenticateService(final PasswordEncoder passwordEncoder,
                               final UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public User authenticate(final String username, final String password) {
        final Optional<User> optionalUser = this.userService.getByUsername(username);
        if (!optionalUser.isPresent() || !this.passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            return null;
        }
        return optionalUser.get();
    }

    public String createJwtToken(final User user) {
        final String jwt = Jwts.builder()
                .setSubject(user.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("name", user.getName())
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        return String.format("%s %s", TOKEN_PREFIX, jwt);
    }

    public UsernamePasswordAuthenticationToken parseJwt(String token) {
        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim()).getBody();
            var user = User.builder().username(claims.get("username", String.class)).name(claims.get("name", String.class));
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, null);
            }
            return null;
        }
        return null;
    }

}
