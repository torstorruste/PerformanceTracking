package org.superhelt.performance.data;

import org.superhelt.performance.valueprovider.ValueProvider;

import java.util.List;

public class QueryBuilder {

    public String createQuery(String reportId, List<ValueProvider> valueProviders) {
        StringBuilder sb = new StringBuilder();

        sb.append("query {\n");
        sb.append(" reportData {\n");
        sb.append("    report(code: \"").append(reportId).append("\") {\n");
        sb.append("      code");
        for(var provider : valueProviders) {
            sb.append(",\n").append(provider.getQueryFragment());
        }

        sb.append("    }\n");
        sb.append("  }\n");
        sb.append("}");

        String query = sb.toString().replace("\\", "\\\\").replace("\"", "\\\"");

        return String.format("{\"query\": \"%s\"}", query);
    }
}
