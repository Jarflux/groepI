package be.kdg.groepi.security;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service("authenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = UserService.getUserByEmail(authentication.getName());
        HttpSession session = request.getSession();
        session.setAttribute("userObject", user);
        try {
            JSONObject jUser = new JSONObject("{\"user\":{\"password\":\"" + user.getPassword() + "\",\"email\":\"" + user.getEmail() + "\",\"name\":\"" + user.getName() + "\",\"id\":\"" + user.getId() + "\",\"dateOfBirth\":\"" + user.getDateOfBirth()+"\"}}");
            response.addHeader("User",jUser.toString());
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/profile/myprofile");
        dispatcher.forward(request,response);
        //response.sendRedirect("/profile/myprofile");
    }
}
