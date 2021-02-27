package org.superhelt.performance.reportprovider;

import com.google.gson.JsonObject;

public interface ValueProvider<T> {
    String getQueryFragment();
    T getValues(JsonObject report);
}
