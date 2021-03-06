package org.superhelt.performance.data;

import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.eventprovider.EventProvider;
import org.superhelt.performance.reportprovider.ValueProvider;

import java.util.List;

public class QueryBuilder {

    public String listReportQuery(int guildId) {
        StringBuilder sb = new StringBuilder();

        sb.append("query { reportData { reports(guildID: ").append(guildId);
        sb.append(", zoneID: 26) { data {code, zone {id, name}}}}}");

        return convertToQuery(sb.toString());
    }

    public String createQuery(String reportId, List<? extends ValueProvider> valueProviders) {
        StringBuilder sb = new StringBuilder();

        sb.append("query {\n");
        sb.append("\treportData {\n");
        sb.append("\t\treport(code: \"").append(reportId).append("\") {\n");
        sb.append("\t\t\tcode");
        for(var provider : valueProviders) {
            sb.append(",\n").append(provider.getQueryFragment());
        }

        sb.append("\t\t}\n");
        sb.append("\t}\n");
        sb.append("}");

        return convertToQuery(sb.toString());
    }

    public String createQuery(Report report, List<EventProvider> eventProviders) {
        StringBuilder sb = new StringBuilder();

        sb.append("query {\n");
        sb.append("\treportData {\n");
        sb.append("\t\treport(code: \"").append(report.getCode()).append("\") {\n");
        sb.append("\t\t\tcode");
        for(var provider : eventProviders) {
            sb.append(",\n").append(provider.getQueryFragment(report));
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
