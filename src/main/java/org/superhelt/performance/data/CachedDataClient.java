package org.superhelt.performance.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
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
        try {
            Path dataFile = reportDirectory.resolve("reports-" + guildId + ".json");
            if (!Files.exists(dataFile)) {
                log.info("{} does not already exist, fetching reports from DataClient", dataFile);
                List<String> reportIds = dataClient.getReportIds(guildId);
                saveData(dataFile, reportIds);
            }
            return loadList(dataFile);
        } catch(IOException e) {
            throw new RuntimeException("Unable to get report Id", e);
        }
    }

    @Override
    public Report getReport(String reportId) {
        try {
            Path dataFile = reportDirectory.resolve(reportId+".json");
            if(!Files.exists(dataFile)) {
                log.info("{} does not already exist, fetching from DataClient", dataFile);
                Report report = dataClient.getReport(reportId);
                saveData(dataFile, report);
            }

            return loadData(dataFile, Report.class);
        } catch (IOException e) {
            log.error("Unable to get report {}", reportId, e);
        }
        throw new RuntimeException("Unable to get report");
    }

    @Override
    public List<WarcraftLogsEvent> getEvents(Report report, List<EventProvider> eventProviders) {
        try {
            Path dataFile = eventDirectory.resolve(report.getCode()+".json");
            if(!Files.exists(dataFile)) {
                log.info("{} does not already exist, fetching events from DataClient", dataFile);
                List<WarcraftLogsEvent> events = dataClient.getEvents(report, eventProviders);
                saveData(dataFile, events);
            }

            return loadEvents(dataFile);
        } catch (IOException e) {
            log.error("Unable to get events for report {}", report.getCode(), e);
        }
        throw new RuntimeException("Unable to get report");
    }

    private <T> void saveData(Path dataFile, T data) throws IOException {
        if(!Files.exists(dataFile.getParent())) {
            log.info("Creating directory {}", dataFile.getParent());
            Files.createDirectory(dataFile.getParent());
        }

        log.info("Writing data to {}", dataFile);
        Files.write(dataFile, gson.toJson(data).getBytes());
    }

    private <T> T loadData(Path dataFile, Class<T> tClass) throws IOException {
        log.info("Fetching data from {}", dataFile);
        return gson.fromJson(readFile(dataFile), tClass);
    }

    private <T> T loadList(Path dataFile) throws IOException {
        log.info("Fetching data from {}", dataFile);
        return gson.fromJson(readFile(dataFile), new TypeToken<ArrayList<T>>(){}.getType());
    }

    public String readFile(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    private List<WarcraftLogsEvent> loadEvents(Path dataFile) throws IOException {
        log.info("Fetching events from {}", dataFile);
        return gson.fromJson(readFile(dataFile), new TypeToken<ArrayList<WarcraftLogsEvent>>(){}.getType());
    }
}
