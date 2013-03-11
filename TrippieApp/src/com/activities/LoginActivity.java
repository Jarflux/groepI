package com.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.*;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        passwordField = (EditText)findViewById(R.id.password);
        usernameField = (EditText)(findViewById(R.id.username));
    }

    @Override
    public void onClick(View view) {
        Editor sessionEditor = getApplicationContext().getSharedPreferences("Session",0).edit();
        String password = passwordField.getText().toString();
        String email = usernameField.getText().toString();
        JSONObject user = controller.springSecurityCheck(email, password);
        if(user != null){
            sessionEditor.putString("User", user.toString());
            sessionEditor.commit();
            Intent intent = new Intent(getApplicationContext(),UserTripsActivity.class);
            startActivity(intent);
        }else{
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setCancelable(false); // This blocks the 'BACK' button
            ad.setMessage("Oh no! Your username or password were incorrect. You'll have to try again.");
            ad.setButton(AlertDialog.BUTTON_NEUTRAL,"OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }
    }

}
