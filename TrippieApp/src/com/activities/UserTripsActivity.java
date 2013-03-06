package com.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.controllers.Controller;
import com.model.TripInstance;

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
        List<TripInstance> trips = Controller.getUserTripParticipations("1");
        List<String> tripNames = new ArrayList<String>();
        for(TripInstance trip: trips){
            tripNames.add(trip.getTitle());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userTrips);
        ListView listView = (ListView) findViewById(R.id.tripList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tripNames);
        listView.setAdapter(adapter);
    }
}