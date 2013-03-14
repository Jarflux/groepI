package be.kdg.groepi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 3-3-13
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        httpServletResponse.addHeader("Validated", "false");
        httpServletResponse.sendRedirect("/login/incorrect");
    }
}
