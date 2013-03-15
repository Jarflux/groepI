package com.activities;

import android.os.Bundle;
import android.widget.TextView;
import com.controllers.Controller;
import com.model.TripInstance;
import com.activities.R;
import com.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 11-3-13
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class TripDetailActivity extends ParentActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usertripdetail);
        addContent();

    }

    @Override
    public void addContent() {
        TripInstance trip = null;
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        if(getIntent().hasExtra("trip")){
        trip = (TripInstance) getIntent().getSerializableExtra("trip");
        TextView textView = (TextView)findViewById(R.id.nameValue);
        textView.setText(trip.getfTitle());
        textView = (TextView)findViewById(R.id.descriptionValue);
        textView.setText(trip.getfDescription());

        textView = (TextView)findViewById(R.id.dateValue);
        cal.setTime(DateUtil.longToDate(trip.getfStartTime()));
        String dateString = date.format(cal.getTime());
        textView.setText(String.valueOf(dateString));

        textView = (TextView)findViewById(R.id.startValue);
        String start = time.format(cal.getTime());
        textView.setText(String.valueOf(start));

        textView = (TextView)findViewById(R.id.endValue);
        cal.setTime(DateUtil.longToDate(trip.getfEndTime()));
        String end = time.format(cal.getTime());
        textView.setText(end);
        }
    }
}