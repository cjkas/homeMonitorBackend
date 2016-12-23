package cz.slaw.hm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import cz.slaw.hm.security.handler.TokenHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by Sławek on 2016-12-10.
 */
@Component
public class TokenAuthenticationService {

    private static final String BEARER = "Bearer ";

    @Autowired
    private TokenHandler tokenHandler;


    public String addAuthentication(String name, Collection<? extends GrantedAuthority> authorities) {
        return tokenHandler.createTokenForUser(name, authorities);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(BEARER)) {
            System.err.println("XXXXXXXXXXXXXXX " + header.substring(BEARER.length(), header.length()));
            final User user = tokenHandler.parseUserFromToken(header.substring(BEARER.length(), header.length()));
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}