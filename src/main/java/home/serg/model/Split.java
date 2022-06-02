package home.serg.model;

import javax.persistence.*;

@Entity
@Table(name = "split")
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
