package home.serg.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    String name;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Chat chat;

}
