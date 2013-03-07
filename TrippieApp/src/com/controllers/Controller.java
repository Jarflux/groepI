package com.controllers;


import android.content.Intent;
import android.preference.PreferenceActivity;
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
    final static String adres = "192.168.1.137:8080";
    private Thread thread = new Thread("New Thread") {
        public void run(){

            }
        };

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

    public static JSONObject springSecurityCheck(String name, String password) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost requestLogin = new HttpPost("http://"+ adres +"/j_spring_security_check?");
        JSONObject user = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("j_username", name));
        params.add(new BasicNameValuePair("j_password", password));

        try {
            requestLogin.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ResponseHandler<String> responseHandler=new BasicResponseHandler();
            String userString = client.execute(requestLogin,responseHandler);
            user = new JSONObject(userString);
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
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