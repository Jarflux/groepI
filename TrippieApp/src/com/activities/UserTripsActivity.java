package com.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.controllers.Controller;
import com.model.TripInstance;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class UserTripsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences session = getApplicationContext().getSharedPreferences("Session", 0);
        JSONObject user = null;
        Long userId = null;
        try {
            user = new JSONObject(session.getString("User", null));
            userId = Long.parseLong(user.getString("Id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<TripInstance> trips = Controller.getUserTripParticipations(userId);
        List<String> tripNames = new ArrayList<String>();
        for (TripInstance trip : trips) {
            tripNames.add(trip.getTitle());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usertrips);
        ListView listView = (ListView) findViewById(R.id.tripList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tripNames);
        listView.setAdapter(adapter);
    }
}