package org.superhelt.performance.valueprovider;

import com.google.gson.JsonObject;

import java.util.List;

public interface ValueProvider<T> {
    String getQueryFragment();
    List<T> getValues(JsonObject data);
}
