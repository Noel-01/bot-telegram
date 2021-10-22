package com.bot.bottelegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BotTelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotTelegramApplication.class, args);
	}

}
