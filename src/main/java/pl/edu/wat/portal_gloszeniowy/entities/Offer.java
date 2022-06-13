package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.edu.wat.portal_gloszeniowy.models.User;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "offer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private float price;
    private String details;
    private Date date;
    @Column(columnDefinition = "int default 0")
    private int status;
    @Column(nullable = true) // FIXME do zmiany po wyczyszczeniu bazy z danych bez tej wartości
    private double lat;
    @Column(nullable = true) // FIXME do zmiany po wyczyszczeniu bazy z danych bez tej wartości
    private double lon;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] photos;

    @OneToMany(mappedBy="offer")
    private List<Comment> comments = new LinkedList<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            joinColumns = { @JoinColumn(name = "offer_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private List<Tag> tagList = new LinkedList<>();


    @ManyToOne
    User user;
}
