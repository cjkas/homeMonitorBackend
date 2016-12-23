package cz.slaw.hm.security.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Sławek on 2016-12-10.
 */
@Component
public final class TokenHandler {

    private static final String secret = "secret-key";
    public static final String ROLES = "roles";

    private final Logger log = LoggerFactory.getLogger(TokenHandler.class);

    public User parseUserFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        String username = claimsJws.getBody().getSubject();
        Collection<String> roles = (Collection<String>) claimsJws.getBody().get(ROLES);
        Set<SimpleGrantedAuthority> authorities = roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toSet());
        log.info("parseClaimsJws {}, {}",username, authorities);
        return new User(username, "", authorities);
    }

    public String createTokenForUser(String name, Collection<? extends GrantedAuthority> authorities) {
        Set<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return Jwts.builder()
                .setSubject(name)
                .claim(ROLES, roles)
                .setIssuedAt(new Date())
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
