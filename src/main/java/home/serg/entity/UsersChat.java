package home.serg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersChat {

    @EmbeddedId
    private UsersChatId usersChatId = new UsersChatId();

    @ManyToOne
    @MapsId(value = "userId")
    @JoinColumn(name = "uses_id")
    private Users users;

    @ManyToOne
    @MapsId(value = "chatId")
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
