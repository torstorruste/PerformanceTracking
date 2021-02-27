package org.superhelt.performance.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class WarcraftLogsClient {

    private static final Logger log = LoggerFactory.getLogger(WarcraftLogsClient.class);

    private static final String clientId = "92d34172-d629-470a-9424-eca16af5f7d1";
    private static final String clientSecret = "mc23qzmh402vryFNShIEFxPkkpcuQzi68x1O3zqN";

    public static void main(String[] args) {
        var token = new WarcraftLogsClient().getToken();

        System.out.println(token);
    }

    private String getToken() {
        try {
            var connection = (HttpURLConnection) new URL("https://www.warcraftlogs.com/oauth/token").openConnection();

            var credentials = new String(Base64.getEncoder().encode(String.format("%s:%s",clientId, clientSecret).getBytes()));
            connection.addRequestProperty("Authorization", String.format("Basic %s", credentials));
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.getOutputStream().write("grant_type=client_credentials".getBytes());


            var responseCode = connection.getResponseCode();

            if(responseCode<400) {
                return getContent(connection.getInputStream());
            } else {
                return getContent(connection.getErrorStream());
            }
        } catch (Exception e) {
            log.error("Unable to get token", e);
            return null;
        }
    }

    private String getContent(InputStream is) {
        try {
            var buf = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = buf.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch(Exception e) {
            log.error("Unable to read content", e);
            return null;
        }
    }
}
