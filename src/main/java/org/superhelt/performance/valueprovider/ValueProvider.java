package org.superhelt.performance.valueprovider;

import com.google.gson.JsonObject;

public interface ValueProvider<T> {
    String getQueryFragment();
    T getValues(JsonObject report);
}
