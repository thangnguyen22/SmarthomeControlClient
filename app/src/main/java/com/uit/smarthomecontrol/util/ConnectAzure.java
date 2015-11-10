package com.uit.smarthomecontrol.util;

import android.os.AsyncTask;
import android.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by tensh on 10/1/2015.
 */
public class ConnectAzure extends AsyncTask<String,String,String> {
    static String sasKey = "fZ1o9h3e6ATP6RcSH35zBWMPKL44N84elFYDoxY9Kjs=";
    static String sasKeyName = "RootManageSharedAccessKey";
    static String uri = "https://smarthomethesis.servicebus.windows.net/";

    String topic = "lighttopic";
    String subscription = "LightSubscription";
    String mess = "testMessage123";

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        // update textview here
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = uri + topic + "/messages";

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            // Add header
            String token = generateSasToken(new URI(uri));
            String retoken = token.replace("%3D%0A&se", "%3D&se");

            post.setHeader("Authorization", retoken);
            post.setHeader("Content-Type", "text/plain");
            post.setHeader(subscription, subscription);
            StringEntity input = new StringEntity(mess);
            post.setEntity(input);

            System.out.println("Llamando al post");
            HttpResponse response = client.execute(post);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            } else {
                return "No string.";
            }
        } catch (Exception e) {
            return "Network problem";
        }

    }
    private static String generateSasToken(URI uri) {
        String targetUri;
        try {
            targetUri = URLEncoder
                    .encode(uri.toString().toLowerCase(), "UTF-8");


            long expiresOnDate = System.currentTimeMillis();
            int expiresInMins = 20; // 1 hour
            expiresOnDate += expiresInMins * 60 * 1000;
            long expires = expiresOnDate / 1000;
            String toSign = targetUri + "\n" + expires;

            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = sasKey.getBytes("UTF-8");
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(toSign.getBytes("UTF-8"));

            String rawHmacStr = new String(Base64.encode(rawHmac, Base64.DEFAULT),"UTF-8");
            String signature = URLEncoder.encode(rawHmacStr, "UTF-8");

            // construct authorization string
            String token = "SharedAccessSignature sr=" + targetUri + "&sig="
                    + signature + "&se=" + expires + "&skn=" + sasKeyName;
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
