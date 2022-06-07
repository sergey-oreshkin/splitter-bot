package home.serg.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class UsersChat {

    @EmbeddedId
    private UsersChatId usersChatId = new UsersChatId();

    @ManyToOne
    @MapsId(value = "usersId")
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @MapsId(value = "chatId")
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
