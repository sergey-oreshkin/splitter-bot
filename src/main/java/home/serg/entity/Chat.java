package home.serg.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"usersChats"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Chat {

    @Id
    private Long chatId;

    private String chatType;

    private String title;

    @CreationTimestamp
    private LocalDateTime update;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private Collection<UsersChat> usersChats = new HashSet<>();

}
