package home.serg.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"users"})
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "chat_type")
    private String chatType;

    @Column(name = "title")
    private String title;

    @Column(name = "update")
    @CreationTimestamp
    private LocalDate update;

    @ManyToMany(mappedBy = "chats")
    private Set<User> users = new HashSet<>();
}
