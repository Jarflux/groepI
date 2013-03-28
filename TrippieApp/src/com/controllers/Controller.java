package com.controllers;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Message;
import com.model.TripInstance;
import com.model.User;
import com.tasks.HttpGetTask;
import com.tasks.HttpPostTask;
import com.tasks.LoginTask;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
    private Gson gson = new Gson();

    public List<TripInstance> getUserTripParticipations(Long userId){
        HttpGetTask getTask = new HttpGetTask();
        List tripParticipations = new ArrayList();
        getTask.execute("android/showUserTripParticipations",userId.toString());
        try {
            if(getTask.get() != null){
                Type collectionType = new TypeToken<List<TripInstance>>(){}.getType();
                List<TripInstance> participations = gson.fromJson((String)getTask.get(),collectionType);
            return participations;
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return tripParticipations;
    }

    public TripInstance getTripInstance(Long tripId){
        HttpGetTask getTask = new HttpGetTask();
        TripInstance trip = null;
        getTask.execute("android/getTripInstance",tripId.toString());
        try {
            if(getTask.get() != null){
                trip = gson.fromJson((String)getTask.get(),TripInstance.class);
                return trip;
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return trip;
    }



    public  JSONObject springSecurityCheck(String username, String password, Context ctx) {
        LoginTask loginTask = new LoginTask(ctx);
        loginTask.execute(username,password);
        try {
            return (JSONObject)loginTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getTripParticipants(Long tripId){
        HttpGetTask getTask = new HttpGetTask();
        getTask.execute("android/getTripParticipants",tripId.toString());
        try {
            if(getTask.get() != null){
                Type collectionType = new TypeToken<List<User>>(){}.getType();
                List<User> participants = gson.fromJson((String)getTask.get(),collectionType);
                return participants;
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public boolean sendMessage(Message message){
    HttpPostTask postTask = new HttpPostTask();
        try{
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            String jMessage = gson.toJson(message);
            parameters.add(new BasicNameValuePair("message",jMessage));
            postTask.execute("android/sendMessage",parameters);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Message> getMessages(){
        Gson gson = new Gson();
        HttpGetTask getTask = new HttpGetTask();
        getTask.execute("android/getMessages","");
        try {
            if(getTask.get() != null){
                Type collectionType = new TypeToken<List<Message>>(){}.getType();
                List<Message> messages = gson.fromJson((String)getTask.get(),collectionType);
                return messages;
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}