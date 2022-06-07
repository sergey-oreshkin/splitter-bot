package home.serg.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public interface MessageProcessor {
    String getResponse(Update update);
}
