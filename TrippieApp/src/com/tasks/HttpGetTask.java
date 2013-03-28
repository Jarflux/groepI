package com.tasks;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.activities.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.TripInstance;
import com.utils.ServerUtil;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 10-3-13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
public class HttpGetTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object... objects) {
        DefaultHttpClient client = new DefaultHttpClient();
        Gson gson = new Gson();
        String items = null;
        HttpGet request = new HttpGet(ServerUtil.getServerAddres(true)+"/"+objects[0]+"/"+objects[1]);
        try {
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = client.execute(request, responseHandler);
            items = responseBody;
        }catch(Exception e){
            e.printStackTrace();
        }
        return items;
    }
}
