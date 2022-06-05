package home.serg.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface MessageProcessor {
    String getResponse(long chat, String msg, User user);
}
