package com.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.controllers.Controller;
import org.json.JSONObject;

public class LoginActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button button;
    private EditText passwordField;
    private EditText usernameField;
    private Controller controller = new Controller();
    private Thread loginThread = new Thread("New Thread") {
        public void run(){
            Editor sessionEditor = getApplicationContext().getSharedPreferences("Session",0).edit();
            String password = passwordField.getText().toString();
            String email = usernameField.getText().toString();
            JSONObject user = controller.springSecurityCheck(email, password);
            if(user != null){
                sessionEditor.putString("User",user.toString());
                sessionEditor.commit();
                Intent intent = new Intent(getApplicationContext(),UserTripsActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        passwordField = (EditText)findViewById(R.id.password);
        usernameField = (EditText)(findViewById(R.id.username));
    }

    @Override
    public void onClick(View view) {

        if(!loginThread.isAlive()){
        loginThread.start();
        }
    }

}
