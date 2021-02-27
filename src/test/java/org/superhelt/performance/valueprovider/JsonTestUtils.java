package org.superhelt.performance.valueprovider;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JsonTestUtils {

    public static JsonObject readFile(String path) throws Exception  {
        var buf = new BufferedReader(new InputStreamReader(JsonTestUtils.class.getResourceAsStream("/"+path)));

        StringBuilder sb = new StringBuilder();
        String line;
        while((line = buf.readLine())!=null) {
            sb.append(line);
        }

        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }
}
