package home.serg.service;

import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.objects.User;

public interface MessageProcessor {
    String getResponse(long chat, String msg, User user);
}
