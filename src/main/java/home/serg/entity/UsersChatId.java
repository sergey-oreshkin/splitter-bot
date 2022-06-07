package home.serg.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class UsersChatId implements Serializable {
    private Integer usersId;
    private Integer chatId;
}
