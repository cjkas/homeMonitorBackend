package cz.slaw.hm.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;
import cz.slaw.hm.dto.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sławek on 2016-12-10.
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return (String) request.getAttribute(getPasswordParameter());
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return (String) request.getAttribute(getUsernameParameter());
    }

    @Override

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        MediaType mediaType = MediaType.parseMediaType(request.getHeader(HttpHeaders.CONTENT_TYPE));
        if (mediaType != null && mediaType.isCompatibleWith(MimeTypeUtils.APPLICATION_JSON)) {

            try {
                LoginRequest loginRequestRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
                request.setAttribute(getUsernameParameter(), loginRequestRequest.getUsername());
                request.setAttribute(getPasswordParameter(), loginRequestRequest.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.attemptAuthentication(request, response);
    }
}
