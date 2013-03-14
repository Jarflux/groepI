package com.controllers;
import com.model.TripInstance;
import com.tasks.HttpGetTask;
import com.tasks.LoginTask;
import org.json.JSONObject;

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
    private final static String adres = "192.168.1.137:8080";

    public List<TripInstance> getUserTripParticipations(Long userId){
        HttpGetTask getTask = new HttpGetTask();
        List tripParticipations = new ArrayList();
        getTask.execute("android/showUserTripParticipations",userId.toString());
        try {
            if(getTask.get() != null){
            return (List)getTask.get();
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
                return (TripInstance)getTask.get();
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return trip;
    }


    public  JSONObject springSecurityCheck(String username, String password) {
        LoginTask loginTask = new LoginTask();
        loginTask.execute(username,password);
        try {
            return (JSONObject)loginTask.get();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}