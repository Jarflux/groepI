package com.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
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
public class UserTripsActivity extends ParentActivity {
    private Controller controller = new Controller();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usertrips);
        SharedPreferences session = getApplicationContext().getSharedPreferences("Session", 0 );
        JSONObject user = null;
        Long userId = null;
        try {
            user = new JSONObject(session.getString("User", null));
            userId = Long.parseLong(user.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<TripInstance> trips = controller.getUserTripParticipations(userId);
        List<String> tripNames = new ArrayList<String>();
        for (TripInstance trip : trips) {
            tripNames.add(trip.getfTitle());
        }
        ListView listView = (ListView) findViewById(R.id.tripList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tripNames);
        listView.setAdapter(adapter);
    }
}