package org.superhelt.performance.reportprovider;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.superhelt.performance.om.Player;
import org.superhelt.performance.om.warcraftlogs.ReportPlayer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerProviderTest {

    @Test
    void canParsePlayers() throws Exception {
        JsonObject json = JsonTestUtils.readFile("playerResponse.json");
        JsonObject report = json.get("data").getAsJsonObject().get("reportData").getAsJsonObject().get("report").getAsJsonObject();

        PlayerProvider provider = new PlayerProvider();

        List<ReportPlayer> actual = provider.getValues(report);

        assertEquals(24, actual.size());
    }
}
