package org.superhelt.performance.om.warcraftlogs;

import org.superhelt.performance.om.Player;

public class ReportPlayer extends Player {

    private final int reportId;

    public ReportPlayer(int id, String name, String playerClass, int reportId) {
        super(id, name, playerClass);
        this.reportId = reportId;
    }

    public int getReportId() {
        return reportId;
    }
}
