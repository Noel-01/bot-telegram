package com.bot.bottelegram.bot;

import com.bot.bottelegram.scraping.WebScraping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Locale;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private WebScraping scraping;

    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return "username";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {

                String text = message.getText().toLowerCase(Locale.ROOT);

                SendMessage sm = new SendMessage();

                sm.setChatId(String.valueOf(message.getChatId()));

                if (text.contains("#noticias")) {
                    sendNews();
                } else if (text.contains("jarvis")){
                    sendWords(sm, text);
                }
            }
        }
    }

    public void sendNews() {
        SendMessage sm = new SendMessage();
        sm.setChatId("-420779853"); // id de mi grupo
        sm.setText(scraping.webScraping().stream().findFirst().get());
        send(sm);

    }

    public void sendWords(SendMessage sm, String text) {
        sm.setText("Jarvis dice lo mismo que tu: " + text);
        send(sm);
    }

    private void send(SendMessage sm) {
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}