package com.activities;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.controllers.Controller;
import com.google.gson.Gson;
import com.model.Message;
import com.model.TripInstance;
import com.model.User;
import com.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 16-3-13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class ChatActivity extends ParentActivity {
    private final Controller controller = new Controller();
    private TextView chatText;
    private EditText chatBar;
    private Button sendBtn;

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            setChatText(chatText);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        chatText = (TextView)findViewById(R.id.chatText);
        chatBar = (EditText)findViewById(R.id.chatBar);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        setChatText(chatText);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences session = getApplicationContext().getSharedPreferences("Session", 0 );
                Gson gson = new Gson();
                String text = chatBar.getText().toString();
                User user = gson.fromJson(session.getString("User",null),User.class);
                Message message = new Message(text,Calendar.getInstance().getTime().getTime(),user);
                controller.sendMessage(message);
                chatBar.setText("");
                setChatText(chatText);
            }
        });

        int delay = 5000; // delay for 5 sec.
        int period = 5000; // repeat every sec.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                TimerMethod();
            }
        }, delay, period);
    }

    private void TimerMethod()
    {
        this.runOnUiThread(Timer_Tick);
    }


    public void setChatText(TextView chatText){
        List<Message> messages = controller.getMessages();
        StringBuilder messageBuilder = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for(Message message: messages){
            String str = String.format("%s: %-50s %s\n\n",message.getfUser().getfName(),message.getfContent(),dateFormat.format(DateUtil.longToDate(message.getfDate())));
            messageBuilder.append(str);
        }
        chatText.setText(messageBuilder.toString());
    }

}