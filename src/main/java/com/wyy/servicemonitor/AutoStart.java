package com.wyy.servicemonitor;

import com.wyy.servicemonitor.config.Monitor;
import com.wyy.servicemonitor.config.MonitorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * @Date: 20-4-3
 * @Author: wyy
 */
@Component
public class AutoStart implements ApplicationRunner {

    private Logger log = LoggerFactory.getLogger(AutoStart.class);

    @Autowired
    private MonitorConfiguration monitorConfiguration;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Monitor> monitorList = monitorConfiguration.getMonitors();
        log.debug("monitor size = " + monitorList.size());
        monitorList.forEach(monitor -> {
            MonitorTask monitorTask = new MonitorTask(monitor);
            Timer timer = new Timer();
            timer.schedule(monitorTask, new Date(), monitor.getPeriod());
        });
    }
}
