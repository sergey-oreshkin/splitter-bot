package home.serg.controller;

import home.serg.service.MessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@RequiredArgsConstructor
@Service
public class SplitBot extends TelegramLongPollingBot {

    private final MessageProcessor messageProcessor;

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

        SendMessage sm = new SendMessage();
        log.info(update.toString());

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            User user = update.getMessage().getFrom();
            sm.setChatId(chatId.toString());
            sm.setText(messageProcessor.getResponse(chatId, text, user));
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace(); //TODO
            }
        } else if (update.getMyChatMember() != null &&
                "member".equals(update.getMyChatMember().getNewChatMember().getStatus())) {
            Long chatId = update.getMyChatMember().getChat().getId();
            sm.setChatId(chatId.toString());
            sm.setText(messageProcessor.getResponse(chatId, "/", null));
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace(); //TODO
            }
        }
    }
}
