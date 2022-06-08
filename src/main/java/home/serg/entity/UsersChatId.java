package home.serg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsersChatId implements Serializable {
    private Long userId;
    private Long chatId;
}
