package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
public class SchedulersController {
    @Autowired
    private SchedulerService schedulerService;

//    @Scheduled(cron = "0 * * * * *")
//    @Scheduled(cron = "0 0/10 * * * *") // every hour of 10 seconds
    @Scheduled(cron = "0 0/55 23 * * *") // every evening at 11.55
//    @Scheduled(cron = "0 */1 * ? * *")      //every 1 minute
    public void autoCheckoutAttendance() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        log.info("autoCheckoutAttendance The time is now {}", dateFormat.format(new Date()));
        System.out.println("autoCheckoutAttendance The time is now {}"+ dateFormat.format(new Date()));
        schedulerService.checkEmployeeOutTime();
    }
}
