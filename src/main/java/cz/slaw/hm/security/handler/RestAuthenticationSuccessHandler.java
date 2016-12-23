package cz.slaw.hm.security.handler;

import cz.slaw.hm.domain.UserEntity;
import cz.slaw.hm.dto.LoginResponse;
import cz.slaw.hm.security.TokenAuthenticationService;
import cz.slaw.hm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import cz.slaw.hm.helper.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenAuthenticationService authenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        UserEntity user = userService.findByLogin(authentication.getName());
        String token = authenticationService.addAuthentication(authentication.getName(), authentication.getAuthorities());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user);
        loginResponse.setToken(token);
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, loginResponse);
    }
}
