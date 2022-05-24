package home.serg.controller;

import home.serg.service.MessageProcessor;
import home.serg.service.MessageProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplitBot extends TelegramLongPollingBot {
    Logger logger = LoggerFactory.getLogger("Bot");

    public String getBotUsername() {
        return "TestSplitting_bot";
    }

    public String getBotToken() {
        try {
            return new BufferedReader(
                    new InputStreamReader(
                            SplitBot.class.getResourceAsStream("/token.txt")
                    )
            ).readLine();
        } catch (IOException e) {
            throw new RuntimeException("Resource with token is not found", e);

        }
    }

    public void onUpdateReceived(Update update) {
        logger.info(update.toString());

        SendMessage sm = new SendMessage();
        MessageProcessor mp = new MessageProcessorImpl();

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            sm.setChatId(chatId.toString());
            sm.setText(mp.getResponse(chatId, text));
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace(); //TODO
            }
        } else if (update.getMyChatMember() != null &&
                "member".equals(update.getMyChatMember().getNewChatMember().getStatus())) {
            Long chatId = update.getMyChatMember().getChat().getId();
            sm.setChatId(chatId.toString());
            sm.setText(mp.getResponse(chatId, "/"));
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace(); //TODO
            }
        }
    }
}
