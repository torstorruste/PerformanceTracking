package org.superhelt.performance.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.eventprovider.EventProvider;
import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.reportprovider.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class WarcraftLogsClient implements DataClient {

    private static final Logger log = LoggerFactory.getLogger(WarcraftLogsClient.class);

    private static final String clientId = "92d34172-d629-470a-9424-eca16af5f7d1";
    private static final String clientSecret = "mc23qzmh402vryFNShIEFxPkkpcuQzi68x1O3zqN";

    private final QueryBuilder queryBuilder;
    private String token;

    public WarcraftLogsClient(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public List<String> getReportIds(int guildId) {
        log.debug("Preparing to fetch report ids for guild {}", guildId);
        if (token == null) {
            token = getToken();
        }
        String query = queryBuilder.listReportQuery(guildId);
        String response = executeQuery(token, query);

        var reports = JsonParser.parseString(response).getAsJsonObject()
                .get("data").getAsJsonObject()
                .get("reportData").getAsJsonObject()
                .get("reports").getAsJsonObject()
                .get("data").getAsJsonArray();

        List<String> result = new ArrayList<>();
        for (var report : reports) {
            result.add(report.getAsJsonObject().get("code").getAsString());
        }
        return result;
    }

    public Report getReport(String reportId) {
        log.debug("Preparing to fetch report with id {}", reportId);
        if (token == null) {
            token = getToken();
        }
        ReportProvider reportProvider = new ReportProvider(new PlayerProvider(), new FightProvider());

        var query = queryBuilder.createQuery(reportId, Collections.singletonList(reportProvider));
        String response = executeQuery(token, query);
        var json = JsonParser.parseString(response).getAsJsonObject()
                .get("data").getAsJsonObject()
                .get("reportData").getAsJsonObject()
                .get("report").getAsJsonObject();

        log.debug("Fetched report with id {}", reportId);
        return reportProvider.getValues(json);
    }

    public List<WarcraftLogsEvent> getEvents(Report report, List<EventProvider> eventProviders) {
        log.debug("Preparing to fetch events for report {}", report.getCode());
        if (token == null) {
            token = getToken();
        }
        var query = queryBuilder.createQuery(report, eventProviders);
        String response = executeQuery(token, query);
        var json = JsonParser.parseString(response).getAsJsonObject()
                .get("data").getAsJsonObject()
                .get("reportData").getAsJsonObject()
                .get("report").getAsJsonObject();

        List<WarcraftLogsEvent> result = new ArrayList<>();
        for (EventProvider provider : eventProviders) {
            result.addAll(provider.getValues(report, json));
        }

        log.debug("Found {} events for report {}", result.size(), report.getCode());
        return result;
    }

    private String getToken() {
        try {
            log.debug("Preparing to fetch access token");
            var connection = (HttpURLConnection) new URL("https://www.warcraftlogs.com/oauth/token").openConnection();

            var credentials = new String(Base64.getEncoder().encode(String.format("%s:%s", clientId, clientSecret).getBytes()));
            connection.addRequestProperty("Authorization", String.format("Basic %s", credentials));
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.getOutputStream().write("grant_type=client_credentials".getBytes());

            var responseCode = connection.getResponseCode();

            if (responseCode < 400) {
                String token = extractToken(getContent(connection.getInputStream()));
                log.debug("Returning token");
                return token;
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
            log.debug("Preparing to execute query");
            var connection = (HttpURLConnection) new URL("https://www.warcraftlogs.com/api/v2/client").openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String replace = query.replace("\n", "").replace("\t", "");
            connection.getOutputStream().write(replace.getBytes());
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            var responseCode = connection.getResponseCode();
            log.debug("Got response {}", responseCode);

            if (responseCode < 400) {
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
        } catch (Exception e) {
            log.error("Unable to read content", e);
            return null;
        }
    }
}
