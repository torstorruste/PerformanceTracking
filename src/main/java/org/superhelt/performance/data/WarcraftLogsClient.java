package org.superhelt.performance.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.valueprovider.FightValueProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Collections;

public class WarcraftLogsClient {

    private static final Logger log = LoggerFactory.getLogger(WarcraftLogsClient.class);

    private static final String clientId = "92d34172-d629-470a-9424-eca16af5f7d1";
    private static final String clientSecret = "mc23qzmh402vryFNShIEFxPkkpcuQzi68x1O3zqN";

    public static void main(String[] args) {
        QueryBuilder builder = new QueryBuilder();
        FightValueProvider provider = new FightValueProvider();
        var query = builder.createQuery("MdPr1Y6VwHWLZ2AB", Collections.singletonList(provider));


        WarcraftLogsClient client = new WarcraftLogsClient();
        var token = client.getToken();

        var response = client.executeQuery(token, query);
        System.out.println(response);

        var fights = provider.getValues(JsonParser.parseString(response).getAsJsonObject());

        System.out.println(fights.size());
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
                return extractToken(getContent(connection.getInputStream()));
            } else {
                throw new RuntimeException(getContent(connection.getErrorStream()));
            }
        } catch (Exception e) {
            log.error("Unable to get token", e);
            return null;
        }
    }

    private String extractToken(String content) {
        JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
        return jsonObject.get("access_token").getAsString();
    }

    private String executeQuery(String token, String query) {
        try {
            var connection = (HttpURLConnection) new URL("https://www.warcraftlogs.com/api/v2/client").openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer "+token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String replace = query.replace("\n", "").replace("\t", "");
            connection.getOutputStream().write(replace.getBytes());
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            var responseCode = connection.getResponseCode();

            if(responseCode<400) {
                return getContent(connection.getInputStream());
            } else {
                throw new RuntimeException(getContent(connection.getErrorStream()));
            }
        } catch (IOException e) {
            log.error("Unable to fetch data", e);
        }
        throw new IllegalArgumentException();
    }

    private String getContent(InputStream is) {
        try {
            var buf = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();
            String line;
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
