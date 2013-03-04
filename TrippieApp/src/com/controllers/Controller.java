package com.controllers;


import android.preference.PreferenceActivity;
import com.model.User;
import com.utils.DateUtil;
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

    /*@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")*/

    public static String login(String credentials) {
        JSONObject jo = null;
        User user = null;
        String email = "";
        String password = "";
        boolean isError = true;
        try {
            jo = new JSONObject(credentials);
            email = jo.getString("username");
            password = jo.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpResponse r = springSecurityCheck(email, password);

        try {
            jo = new JSONObject(r.getFirstHeader("User").getValue());
            password = jo.getJSONObject("user").getString("password");
            email = jo.getJSONObject("user").getString("email");
            String name = jo.getJSONObject("user").getString("name");
            Long dateOfBirth = Long.parseLong(jo.getJSONObject("user").getString("dateOfBirth"));
            Long id = Long.parseLong(jo.getJSONObject("user").getString("id"));
            user = new User(id,name,email,password,dateOfBirth);
        } catch (JSONException e) {
            user=null;
        }
        if(user != null){
            isError = false ;
        }


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
        System.out.println(" ----- Login from" + email
                + " failed----- ");
        return "newLogin";

    }

    public static HttpResponse springSecurityCheck(String name, String password) {

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost requestLogin = new HttpPost("http://192.168.1.137:8080/j_spring_security_check?");
        HttpResponse response = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("j_username", name));
        params.add(new BasicNameValuePair("j_password", password));

        try {
            requestLogin.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = client.execute(requestLogin);
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
}