package com.controllers;


import android.content.Intent;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.util.Log;
import com.activities.UserTripsActivity;
import com.model.TripInstance;
import com.model.User;
import com.utils.DateUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 1-3-13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    final static String adres = "tomcat.vincentverbist.be:8080/";

    public static List<TripInstance> getUserTripParticipations(Long userId){
        JSONObject jTrips = doRequest("trips/showUserTripParticipations",userId.toString());
        List<TripInstance> trips = new ArrayList<TripInstance>();

        /*try {
            InputStream stream = response.getEntity().getContent();
            JSONObject jo = new JSONObject(stream.toString());
        }catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
        return  trips;
    }

    public static JSONObject springSecurityCheck(String username, String password) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost requestLogin = new HttpPost("http://"+ adres +"/j_spring_security_check?");
        JSONObject jUser = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("j_username", username));
        params.add(new BasicNameValuePair("j_password", password));

        try {
            requestLogin.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = client.execute(requestLogin);
            String user = response.getFirstHeader("user").getValue();
            jUser = new JSONObject(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jUser;

        // Set the username and password for creating a Basic Auth request
        HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAuthorization(authHeader);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        try {
            // Make the HTTP GET request to the Basic Auth protected URL
            ResponseEntity<Message> response = restTemplate.exchange("dkd", HttpMethod.GET, requestEntity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
            // Handle 401 Unauthorized response
        }
    }

    public static JSONObject doRequest(String url, String urlParameter){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost requestLogin = new HttpPost("http://"+adres+"/"+url+"/"+urlParameter);
        JSONObject jo = null;
        try {
            ResponseHandler<String> responseHandler=new BasicResponseHandler();
            String responseBody = client.execute(requestLogin, responseHandler);
            jo = new JSONObject(responseBody);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jo;
    }
}