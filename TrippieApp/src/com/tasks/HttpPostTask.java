package com.tasks;

import android.os.AsyncTask;
import com.utils.ServerUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 21-3-13
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
public class HttpPostTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object... parameters) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost messagePost = new HttpPost(ServerUtil.getServerAddres(false) + "/" + parameters[0]);
        JSONObject jUser = null;
        try {
            messagePost.setEntity(new UrlEncodedFormEntity((List<NameValuePair>)parameters[1], HTTP.UTF_8));
            HttpResponse response = client.execute(messagePost);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jUser;
    }
}
