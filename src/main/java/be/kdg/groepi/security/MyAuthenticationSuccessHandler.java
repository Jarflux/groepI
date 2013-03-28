package be.kdg.groepi.security;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

@Service("authenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    protected UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userService.getUserByEmail(authentication.getName());
        Gson gson = new Gson();
        HttpSession session = request.getSession();
        session.setAttribute("userObject", user);
        String jUser = gson.toJson(user);
        response.addHeader("user", jUser);
        // Check of er een redirect gebeurde omdat user nog niet ingelogd was
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        RequestDispatcher dispatcher;
        // Er is een redirect gebeurd, fetch de URI en zet die als bestemming
        if (savedRequest != null) {
            Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
            Matcher matcher = pattern.matcher(savedRequest.getRedirectUrl());
            matcher.find();
            String uri = matcher.group(4);
            dispatcher = request.getRequestDispatcher(uri);
        } // Geen redirect, dus gewoon profiel pagina tonen
        else {
            dispatcher = request.getRequestDispatcher("/profile/myprofile");
        }
        dispatcher.forward(request, response);
    }
}
