package org.superhelt.performance.data;

import org.superhelt.performance.om.Report;
import org.superhelt.performance.eventprovider.EventProvider;
import org.superhelt.performance.reportprovider.ValueProvider;

import java.util.List;

public class QueryBuilder {

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

        String query = sb.toString().replace("\\", "\\\\").replace("\"", "\\\"");

        return String.format("{\"query\": \"%s\"}", query);
    }

    public String createQuery(Report report, List<EventProvider> eventProviders) {
        StringBuilder sb = new StringBuilder();

        sb.append("query {\n");
        sb.append("\treportData {\n");
        sb.append("\t\treport(code: \"").append(report.getCode()).append("\") {\n");
        sb.append("\t\t\tcode");
        for(var provider : eventProviders) {
            sb.append(",\n").append(provider.getQueryFragment(null));
        }

        sb.append("\t\t}\n");
        sb.append("\t}\n");
        sb.append("}");

        String query = sb.toString().replace("\\", "\\\\").replace("\"", "\\\"");

        return String.format("{\"query\": \"%s\"}", query);
    }
}
