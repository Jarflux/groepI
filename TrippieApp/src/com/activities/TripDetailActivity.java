package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.model.TripInstance;
import com.utils.DateUtil;
import com.qualcomm.QCARSamples.CloudRecognition.CloudReco;

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
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            Button startBtn = (Button)findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), CloudReco.class);
                startActivity(intent);
            }
        });
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