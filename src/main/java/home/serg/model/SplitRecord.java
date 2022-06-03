package home.serg.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "split_record")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SplitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Split split;

    @ManyToOne
    private User whoPaid;

    @Column(name = "share")
    private String share;
}