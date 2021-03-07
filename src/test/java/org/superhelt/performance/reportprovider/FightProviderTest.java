package org.superhelt.performance.reportprovider;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.superhelt.performance.om.warcraftlogs.Fight;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FightProviderTest {
    @Test
    void canParseFights() throws Exception {
        JsonObject object = JsonTestUtils.readFile("fightResponse.json");
        JsonObject report = object.get("data").getAsJsonObject().get("reportData").getAsJsonObject().get("report").getAsJsonObject();

        FightProvider provider = new FightProvider();
        List<Fight> actual = provider.getValues(report);

        assertEquals(35, actual.size());
    }
}