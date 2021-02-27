package org.superhelt.performance.reportprovider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FightProviderTest {
    @Test
    void canParseFights() throws Exception {
        var object = JsonTestUtils.readFile("fightResponse.json");
        var report = object.get("data").getAsJsonObject().get("reportData").getAsJsonObject().get("report").getAsJsonObject();

        var provider = new FightProvider();
        var actual = provider.getValues(report);

        assertEquals(35, actual.size());
    }
}