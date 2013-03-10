package com.controllers;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.util.EventLog;
import android.util.Log;
import com.activities.UserTripsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.TripInstance;
import com.model.User;
import com.utils.DateUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 1-3-13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private final static String adres = "192.168.1.137:8080";
    private static Gson gson = new Gson();

    public List<TripInstance> getUserTripParticipations(Long userId){
        HttpAsyncTask requestTask = new HttpAsyncTask();
        requestTask.execute("android/showUserTripParticipations",userId.toString());
        try {
            return (List)requestTask.get();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public  JSONObject springSecurityCheck(String username, String password) {
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
    }

    private class HttpAsyncTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object... objects) {
            DefaultHttpClient client = new DefaultHttpClient();
            List<TripInstance> trips = null;
            HttpGet requestLogin = new HttpGet("http://"+adres+"/"+objects[0]+"/"+objects[1]);
            try {
                ResponseHandler<String> responseHandler=new BasicResponseHandler();
                String responseBody = client.execute(requestLogin, responseHandler);
                Type collectionType = new TypeToken<List<TripInstance>>(){}.getType();
                trips = gson.fromJson(responseBody,collectionType);
            }catch(Exception e){
                e.printStackTrace();
            }
            return trips;
        }
    }
}