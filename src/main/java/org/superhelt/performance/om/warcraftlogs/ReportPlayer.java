package org.superhelt.performance.om.warcraftlogs;

import org.superhelt.performance.om.Player;
import org.superhelt.performance.om.PlayerClass;

public class ReportPlayer extends Player {

    private final int reportId;

    public ReportPlayer(int id, String name, PlayerClass playerClass, int reportId) {
        super(id, name, playerClass);
        this.reportId = reportId;
    }

    public int getReportId() {
        return reportId;
    }
}
