package be.kdg.groepi.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Vincent
 * Date: 12/03/13
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
public class VuforiaUtil {

    public static String accessKey = "dcb738253b204e4ca1626a024da20a3cf697a15f";
    public static String secretKey = "c036f9bc82ce53b0da82d9a3fcba5041519d8476";
    public static String url = "https://vws.qualcomm.com";
    private final float pollingIntervalMinutes = 60;//poll at 1-hour interval

    public static String postTarget(File image, Long stopid) throws URISyntaxException, ClientProtocolException, IOException, JSONException {
        HttpPost postRequest = new HttpPost();
        HttpClient client = new DefaultHttpClient();
        postRequest.setURI(new URI(url + "/targets"));
        JSONObject requestBody = new JSONObject();
        System.out.println("Ik kom binnen in posttarget");
        setRequestBody(requestBody, image, stopid);
        postRequest.setEntity(new StringEntity(requestBody.toString()));
        setHeaders(postRequest); // Must be done after setting the body

        HttpResponse response = client.execute(postRequest);
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);

        JSONObject jobj = new JSONObject(responseBody);

        String uniqueTargetId = jobj.has("target_id") ? jobj.getString("target_id") : "";
        System.out.println("\nCreated target with id: " + uniqueTargetId);

        return uniqueTargetId;
    }

    public static void setRequestBody(JSONObject requestBody, File imageFile, Long stopId) throws IOException, JSONException {


        byte[] image = FileUtils.readFileToByteArray(imageFile);
        String uuid = UUID.randomUUID().toString();

        requestBody.put("name", uuid.replace("-", "")); // Mandatory
        requestBody.put("width", 200); // Mandatory
        requestBody.put("image", Base64.encodeBase64String(image)); // Mandatory
        requestBody.put("active_flag", 1); // Optional
        requestBody.put("application_metadata", Base64.encodeBase64String((stopId + "").getBytes())); // Optional
    }

    public static void setHeaders(HttpUriRequest request) {

        request.setHeader(new BasicHeader("Date", DateUtils.formatDate(new Date()).replaceFirst("[+]00:00$", "")));
        request.setHeader(new BasicHeader("Content-Type", "application/json"));
        request.setHeader("Authorization", "VWS " + accessKey + ":" + VuforiaSignatureUtil.tmsSignature(request, secretKey));
    }
}
