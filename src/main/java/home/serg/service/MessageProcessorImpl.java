package home.serg.service;

import home.serg.entity.*;
import home.serg.repisitory.*;
import home.serg.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProcessorImpl implements MessageProcessor {

    final private ChatDao chatDao;
    final private UsersDao usersDao;
    final private UsersChatDao usersChatDao;
    final private SplitDao splitDao;

    final private SplitRecordDao splitRecordDao;

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

                if (usersChatDao.findById(usersChatId).isPresent()) {
                    response = "Я тебя уже знаю, " + parseName(u);
                } else {
                    chatDao.save(chat);
                    user.setUsersChats(Set.of(usersChat));
                    usersDao.save(user);
                    response = "Я тебя запомнил, " + parseName(u);
                }
                break;
            case "/all":
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
                String usersString = users.stream()
                        .map(Users::getNicName)
                        .collect(Collectors.joining(", ")
                        );

                response = usersString + "\nЭто все кого я знаю";
                break;
            case "/split":
                chat = Chat.builder()
                        .chatId(update.getMessage().getChatId())
                        .title(update.getMessage().getChat().getTitle())
                        .chatType(update.getMessage().getChat().getType())
                        .build();

                u = update.getMessage().getFrom();

                user = Users.builder()
                        .userId(u.getId())
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .nicName(u.getUserName())
                        .build();
                usersChatId = new UsersChatId(user.getUserId(), chat.getChatId());

                usersChat = new UsersChat();
                usersChat.setUsers(user);
                usersChat.setChat(chat);

                if (parts.length == 1) { // /split (все сплиты)
                    List<Split> splits = splitDao.findAllByUsersChat(usersChat);
                    if (splits.isEmpty()) {
                        response = "Ни одного сплита в этом чате";
                    } else {
                        response = splits.stream()
                                .map(Split::getName)
                                .collect(Collectors.joining(", "));
                    }
                } else if (parts.length == 2) {
                    if (parts[1].startsWith("-")){ // /split -цифра
                        //TODO удаление записи
                    } else { // /split имя
                        Optional<Split> splitOptional = splitDao.findSplitByName(parts[1]);
                        if (splitOptional.isPresent()){
                            response = "такой сплит есть"; //TODO вывод записей
                        } else {
                            usersChat = usersChatDao.findById(usersChatId).get();
                            Split split = Split.builder()
                                    .name(parts[1])
                                    .usersChat(usersChat)
                                    .build();
                            splitDao.save(split);
                            response = "Сплит " + parts[1] + " сохранен";
                        }
                    }
                } else { // /split имя сумма [@nicname:доля .. @nicname:доля] (создание записи)

                }


                break;
        }
        return response;
    }

    private String parseName(User user) {//Todo refactor
        return user.getFirstName() + " " + user.getLastName();
    }

    static class Parts{

    }
}
