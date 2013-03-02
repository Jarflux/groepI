package trippie.controller;


import android.preference.PreferenceActivity;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 1-3-13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    RestTemplate restTemplate;

    /*@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")*/

    public static String login(String credentials) {
        JSONObject jo = null;
        String name = "";
        String password = "";
        try {
            jo = new JSONObject(credentials);
            jo.put("password","supersecret");
            name = jo.getString("username");
            password = jo.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpResponse r = springSecurityCheck(name, password);
        for (Header h : r.getAllHeaders()) {
            System.out.println(h.getName() + " " + " " + h.getValue() + "");
        }

        String s = r.getFirstHeader("Location").toString();
        boolean isError = s.contains("login_error");

        if (!isError) {
            Header[] cookies = r.getHeaders("Set-Cookie");
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].toString().contains(
                        "SPRING_SECURITY_REMEMBER_ME_COOKIE")) {
                    String[] cookie = cookies[i].toString().split("=");
                    String token = cookie[1].substring(0,
                            cookie[1].indexOf(";"));
                    if (token != null) {
                        return "token:" + token;
                    }
                }
            }
        }
        System.out.println(" ----- Login from" + name
                + " failed----- ");
        return "newLogin";

    }

    public static HttpResponse springSecurityCheck(String name, String password) {

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost requestLogin = new HttpPost(
                "http://localhost:8080/j_spring_security_check?");
        HttpResponse response = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("j_username", name));
        params.add(new BasicNameValuePair("j_password", password));
        params.add(new BasicNameValuePair("_spring_security_remember_me","true"));
        try {
            requestLogin
                    .setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = client.execute(requestLogin);
            return response;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
