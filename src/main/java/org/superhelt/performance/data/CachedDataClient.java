package org.superhelt.performance.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.eventprovider.EventProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CachedDataClient implements DataClient {

    private static final Logger log = LoggerFactory.getLogger(CachedDataClient.class);

    private final DataClient dataClient;
    private final Path reportDirectory;
    private final Path eventDirectory;
    private final Gson gson;

    public CachedDataClient(DataClient dataClient) {
        this.dataClient = dataClient;
        this.reportDirectory = Paths.get("data");
        this.eventDirectory = Paths.get("events");
        this.gson = new Gson();
    }

    @Override
    public List<String> getReportIds(int guildId) {
        return dataClient.getReportIds(guildId);
    }

    @Override
    public Report getReport(String reportId) {
        try {
            Path dataFile = reportDirectory.resolve(reportId+".json");
            if(!Files.exists(dataFile)) {
                log.info("{} does not already exist, fetching from DataClient", dataFile);
                Report report = dataClient.getReport(reportId);
                saveReport(dataFile, report);
            }

            return loadReport(dataFile);
        } catch (IOException e) {
            log.error("Unable to get report {}", reportId, e);
        }
        throw new RuntimeException("Unable to get report");
    }

    @Override
    public List<Event> getEvents(Report report, List<EventProvider> eventProviders) {
        try {
            Path dataFile = eventDirectory.resolve(report.getCode()+".json");
            if(!Files.exists(dataFile)) {
                log.info("{} does not already exist, fetching events from DataClient", dataFile);
                List<Event> events = dataClient.getEvents(report, eventProviders);
                saveEvents(dataFile, events);
            }

            return loadEvents(dataFile);
        } catch (IOException e) {
            log.error("Unable to get events for report {}", report.getCode(), e);
        }
        throw new RuntimeException("Unable to get report");
    }

    private List<Event> loadEvents(Path dataFile) throws IOException {
        log.info("Fetching events from {}", dataFile);
        return gson.fromJson(readFile(dataFile), new TypeToken<ArrayList<Event>>(){}.getType());
    }

    private void saveEvents(Path dataFile, List<Event> events) throws IOException {
        if(!Files.exists(eventDirectory)) {
            log.info("Creating directory {}", eventDirectory);
            Files.createDirectory(eventDirectory);
        }

        log.info("Writing events to {}", dataFile);
        Files.write(dataFile, gson.toJson(events).getBytes());
    }

    private Report loadReport(Path dataFile) throws IOException {
        log.info("Loading report from {}", dataFile);
        return gson.fromJson(readFile(dataFile), Report.class);
    }

    private void saveReport(Path dataFile, Report report) throws IOException {
        if (!Files.exists(reportDirectory)) {
            log.info("Creating directory {}", eventDirectory);
            Files.createDirectory(reportDirectory);
        }

        log.info("Writing report to {}", dataFile);
        Files.write(dataFile, gson.toJson(report).getBytes());
    }

    public String readFile(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }
}
