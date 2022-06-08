package home.serg.service;

import home.serg.entity.Chat;
import home.serg.entity.Users;
import home.serg.entity.UsersChat;
import home.serg.entity.UsersChatId;
import home.serg.repisitory.ChatDao;
import home.serg.repisitory.UsersChatDao;
import home.serg.repisitory.UsersDao;
import home.serg.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProcessorImpl implements MessageProcessor {

    final private ChatDao chatDao;
    final private UsersDao usersDao;

    final private UsersChatDao usersChatDao;

    @Override
    public String getResponse(Update update) {
        String[] parts = update.getMessage().getText().split(" ");
        String response = Messages.NOTHING_TO_SAY;
        switch (parts[0]) {
            case "/?":
                response = Messages.GREETING;
                break;
            case "/me":
                Chat chat = Chat.builder()
                        .chatId(update.getMessage().getChatId())
                        .title(update.getMessage().getChat().getTitle())
                        .chatType(update.getMessage().getChat().getType())
                        .build();

                User u = update.getMessage().getFrom();
                Users user = Users.builder()
                        .userId(u.getId())
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .nicName(u.getUserName())
                        .build();
                UsersChatId usersChatId = new UsersChatId(user.getUserId(), chat.getChatId());
                UsersChat usersChat = new UsersChat();
                usersChat.setUsers(user);
                usersChat.setChat(chat);

                if(usersChatDao.findById(usersChatId).isPresent()){
                    response = "Я тебя уже знаю, " + parseName(u);
                } else {
                    chatDao.save(chat);
                    user.setUsersChats(Set.of(usersChat));
                    usersDao.save(user);
                    response = "Я тебя запомнил, " + parseName(u);
                }

                break;
            case "/all":
//
                Chat chat1 = Chat.builder()
                        .chatId(update.getMessage().getChatId())
                        .title(update.getMessage().getChat().getTitle())
                        .chatType(update.getMessage().getChat().getType())
                        .build();
                List<Users> users = (List<Users>) usersDao.findAllById(
                        usersChatDao.findAllByChat(chat1).stream()
                                .map(UsersChat::getUsers)
                                .map(Users::getUserId)
                                .collect(Collectors.toList())
                );
                String usersString = users.stream().map(Users::getNicName).collect(Collectors.joining(","));

                response = usersString +  "\nЭто все кого я знаю";
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

    private String parseName(User user) {//Todo refactor
        return user.getFirstName() + " " + user.getLastName();
    }
}
