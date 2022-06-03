package home.serg.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "split")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinTable(
            name = "user_chat",
            joinColumns = { @JoinColumn(
                    table = "user_chat",
                    name = "id",
                    referencedColumnName = "owner"
            )}
    )
    private UserChat owner;
}
