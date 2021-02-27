package org.superhelt.performance.reportprovider;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerProviderTest {

    @Test
    void canParsePlayers() throws Exception {
        JsonObject json = JsonTestUtils.readFile("playerResponse.json");
        var report = json.get("data").getAsJsonObject().get("reportData").getAsJsonObject().get("report").getAsJsonObject();

        var provider = new PlayerProvider();

        var actual = provider.getValues(report);

        assertEquals(24, actual.size());
    }
}
