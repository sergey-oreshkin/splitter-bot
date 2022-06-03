package home.serg.service;

import home.serg.repository.UserRepo;
import home.serg.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@RequiredArgsConstructor
public class MessageProcessorImpl implements MessageProcessor {

    private final UserRepo userRepo;

    @Override
    public String getResponse(long chat, String msg, User user) {
        String[] parts = msg.split(" ");
        String response = Messages.NOTHING_TO_SAY;
        switch (parts[0]) {
            case "/?":
                response = Messages.GREETING;
                break;
            case "/me":
                response = "Я тебя запомнил, " + parseName(parts, user);
                break;
            case "/all":
                response = "Это все кого я знаю";
                break;
            case "/spend":
                response = "Я это учту";
                break;
            case "/calc":
                response = "Посчитаю, но попозже";
                break;
        }
        return response;
    }

    private String parseName(String[] parts, User user) {
        if (parts.length == 2) {
            return parts[1];
        }
        if (parts.length == 3) {
            return parts[1] + " " + parts[2];
        }
        return user.getFirstName() + " " + user.getLastName();//Todo use nic
    }
}
