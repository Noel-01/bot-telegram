package com.bot.bottelegram.scheduler;

import com.bot.bottelegram.bot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTaskNews {

    @Autowired
    private Bot bot;

    @Scheduled(cron = "0 0 10 * * *") // seg min hora * * * todos los días a la 10
    public void checkNews() {
        bot.sendNews();
    }
}
