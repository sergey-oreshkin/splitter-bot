package home.serg.service;

import home.serg.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProcessorImpl implements MessageProcessor {

//    final private ChatDao chatDao;
//    final private UsersDao usersDao;

    @Override
    public String getResponse(Update update) {
        String[] parts = update.getMessage().getText().split(" ");
        String response = Messages.NOTHING_TO_SAY;
        switch (parts[0]) {
            case "/?":
                response = Messages.GREETING;
                break;
            case "/me":
//                Chat chat = Chat.builder()
//                        .chatId(update.getMessage().getChatId())
//                        .title(update.getMessage().getChat().getTitle())
//                        .chatType(update.getMessage().getChat().getType())
//                        .build();
//                chatDao.save(chat);
                User u = update.getMessage().getFrom();
//                Users user = Users.builder()
//                        .userId(u.getId())
//                        .firstName(u.getFirstName())
//                        .lastName(u.getLastName())
//                        .nicName(u.getUserName())
//                        .build();
//                usersDao.save(user);
                response = "Я тебя запомнил, " + parseName(parts, u);
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

    private String parseName(String[] parts, User user) {//Todo delete
        if (parts.length == 2) {
            return parts[1];
        }
        if (parts.length == 3) {
            return parts[1] + " " + parts[2];
        }
        return user.getFirstName() + " " + user.getLastName();
    }
}
