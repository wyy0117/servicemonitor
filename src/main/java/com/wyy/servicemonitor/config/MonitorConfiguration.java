package com.wyy.servicemonitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
@Configuration
@ConfigurationProperties(value = "service")
public class MonitorConfiguration {

    private List<Monitor> monitors = new ArrayList<>();

    @Override
    public String toString() {
        return "MonitorConfiguration{" +
                "monitors=" + monitors +
                '}';
    }

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public MonitorConfiguration setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
        return this;
    }
}
