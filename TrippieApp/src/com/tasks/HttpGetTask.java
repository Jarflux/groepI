package com.tasks;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.TripInstance;
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
        List<TripInstance> trips = null;
        HttpGet requestLogin = new HttpGet("tomcat.vincentverbist.be:8080/"+objects[0]+"/"+objects[1]);
        try {
            ResponseHandler<String> responseHandler=new BasicResponseHandler();
            String responseBody = client.execute(requestLogin, responseHandler);
            Type collectionType = new TypeToken<List<TripInstance>>(){}.getType();
            trips = gson.fromJson(responseBody,collectionType);
        }catch(Exception e){
            e.printStackTrace();
        }
        return trips;
    }
}
