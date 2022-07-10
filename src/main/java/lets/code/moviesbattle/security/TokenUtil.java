package lets.code.moviesbattle.security;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Service
@AllArgsConstructor
public class TokenUtil {

    public Long getIdUser(HttpServletRequest request) throws ParseException {
        final JWTClaimsSet claims = this.getClaimSet(request);
        return Long.parseLong(claims.getClaim("id").toString());
    }

    private JWTClaimsSet getClaimSet(HttpServletRequest request) throws ParseException {
        final String header = request.getHeader("Authorization");
        final String token = header.replace("Bearer ", "").trim();
        final SignedJWT signedToken = SignedJWT.parse(token);
        return signedToken.getJWTClaimsSet();
    }

}
