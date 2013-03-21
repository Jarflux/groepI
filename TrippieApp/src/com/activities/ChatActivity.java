package com.activities;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import com.model.TripInstance;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 16-3-13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class ChatActivity extends ParentActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        Spinner tripDrp = (Spinner) findViewById(R.id.tripDrp);
        Spinner participantDrp = (Spinner) findViewById(R.id.participantDrp);
        ArrayAdapter<TripInstance> adapter = new ArrayAdapter<TripInstance>(this, android.R.layout.simple_list_item_1, trips);
        friendlist.setAdapter(adapter);
    }
}