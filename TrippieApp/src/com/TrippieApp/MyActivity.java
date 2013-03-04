package com.TrippieApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.controllers.Controller;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button button;
    private EditText passwordField;
    private EditText usernameField;

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
        Thread thread = new Thread("New Thread") {
            public void run(){
                String password = passwordField.getText().toString();
                String username = usernameField.getText().toString();
                Controller.login("{\"password\":\"" + password + "\",\"username\":\"" + username + "\"}");
            }
        };

        thread.start();

    }

}
