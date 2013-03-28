package com.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.controllers.Controller;
import com.google.gson.Gson;
import com.model.TripInstance;
import com.model.User;

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
        stylePage();
        addContent();

    }

    @Override
    public void stylePage() {
        setContentView(R.layout.usertrips);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void addContent(){
        SharedPreferences session = getApplicationContext().getSharedPreferences("Session", 0 );
        Gson gson = new Gson();
        User user = gson.fromJson(session.getString("User",null),User.class);
        List<TripInstance> trips = controller.getUserTripParticipations(user.getfId());
        List<String> tripNames = new ArrayList<String>();
        for (TripInstance trip : trips) {
            tripNames.add(trip.getfTitle());
        }

        ListView listView = (ListView) findViewById(R.id.tripList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(),TripDetailActivity.class);
                intent.putExtra("trip",((TripInstance)parent.getItemAtPosition(position)));
                startActivity(intent);
            }
        });
        ArrayAdapter<TripInstance> adapter = new ArrayAdapter<TripInstance>(this, android.R.layout.simple_list_item_1, trips);
        listView.setAdapter(adapter);
    }
}
