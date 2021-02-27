package org.superhelt.performance.valueprovider;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FightValueProviderTest {
    @Test
    void canParseFights() throws Exception {
        var object = readFile("fightResponse.json");

        var provider = new FightValueProvider();
        var actual = provider.getValues(object);

        assertEquals(35, actual.size());
    }

    private JsonObject readFile(String path) throws Exception  {
        var buf = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/"+path)));

        StringBuilder sb = new StringBuilder();
        String line;
        while((line = buf.readLine())!=null) {
            sb.append(line);
        }

        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }
}