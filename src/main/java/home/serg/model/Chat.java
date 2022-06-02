package home.serg.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat")
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long chat_id;

    private String chat_type;

    private String title;

    @CreationTimestamp
    private Date updated;

    @ManyToMany(mappedBy = "users")
    private Set<User> users = new HashSet<>();
}
