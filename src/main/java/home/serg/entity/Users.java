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
public class Users {

    @Id
    private Long userId;

    private String firstName;

    private String lastName;

    private String nicName;

    @CreationTimestamp
    private LocalDateTime update;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.ALL})
    private Collection<UsersChat> usersChats = new HashSet<>();

}
