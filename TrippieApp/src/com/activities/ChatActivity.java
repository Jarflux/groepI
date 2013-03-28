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
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 16-3-13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class ChatActivity extends ParentActivity {
    private final Controller controller = new Controller();
    //private TextView chatText;
    private ListView messageList;
    private EditText chatBar;
    private Button sendBtn;

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            setChatText(messageList);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        messageList = (ListView)findViewById(R.id.messageList);
        chatBar = (EditText)findViewById(R.id.chatBar);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        setChatText(messageList);
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
                setChatText(messageList);
            }
        });

        int delay = 5000; // delay for 5 sec.
        int period = 10000; // repeat every sec.
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


    public void setChatText(ListView messageList){
        List<Message> messages = controller.getMessages();
        List<String> messageStrings = new ArrayList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for(Message message: messages){
            String str = String.format("%s:\n%s\n%s",message.getfUser().getfName(),message.getfContent(),dateFormat.format(DateUtil.longToDate(message.getfDate())));
            messageStrings.add(str);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messageStrings);
        messageList.setAdapter(adapter);
    }

}