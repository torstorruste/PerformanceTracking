package org.superhelt.performance;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class PerformanceApplication extends ResourceConfig {

    public PerformanceApplication() {
        packages("org.superhelt.performance");
    }
}
