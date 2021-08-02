package org.superhelt.performance.data;

import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.eventprovider.EventProvider;
import org.superhelt.performance.reportprovider.ValueProvider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QueryBuilder {

    private final int zoneId = 28;

    public String listReportQuery(int guildId) {
        StringBuilder sb = new StringBuilder();

        sb.append("query { reportData { reports(guildID: ").append(guildId);
        sb.append(", zoneID: ").append(zoneId).append(") { data {code, zone {id, name}}}}}");

        return convertToQuery(sb.toString());
    }

    public String createQuery(String reportId, List<? extends ValueProvider> valueProviders) {
        StringBuilder sb = new StringBuilder();

        sb.append("query {\n");
        sb.append("\treportData {\n");
        sb.append("\t\treport(code: \"").append(reportId).append("\") {\n");
        sb.append("\t\t\tcode");
        for(ValueProvider provider : valueProviders) {
            sb.append(",\n").append(provider.getQueryFragment());
        }

        sb.append("\t\t}\n");
        sb.append("\t}\n");
        sb.append("}");

        return convertToQuery(sb.toString());
    }

    public String createQuery(Report report, List<EventProvider> eventProviders) {
        return createQuery(report, eventProviders, Collections.emptyMap());
    }

    public String createQuery(Report report, List<EventProvider> eventProviders, Map<EventProvider, Integer> startTimes) {
        StringBuilder sb = new StringBuilder();

        sb.append("query {\n");
        sb.append("\treportData {\n");
        sb.append("\t\treport(code: \"").append(report.getCode()).append("\") {\n");
        sb.append("\t\t\tcode");
        for(EventProvider provider : eventProviders) {
            sb.append(",\n").append(provider.getQueryFragment(report, startTimes.getOrDefault(provider, 0)));
        }

        sb.append("\t\t}\n");
        sb.append("\t}\n");
        sb.append("}");


        return convertToQuery(sb.toString());
    }

    private String convertToQuery(String query) {
        return String.format("{\"query\": \"%s\"}", query.replace("\\", "\\\\").replace("\"", "\\\""));
    }
}
