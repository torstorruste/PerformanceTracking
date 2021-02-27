package org.superhelt.performance.valueprovider;

import org.superhelt.performance.om.Event;

import java.util.List;

public abstract class EventProvider implements ValueProvider<List<Event>> {

    protected final String name;

    public EventProvider(String name) {
        this.name = name;
    }
}
