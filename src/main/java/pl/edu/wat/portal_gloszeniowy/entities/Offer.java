package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.*;
import pl.edu.wat.portal_gloszeniowy.models.User;

import javax.persistence.*;
import java.util.Date;
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
    @Lob
    @Column(columnDefinition = "BIGINT")
    private String photos;

    @OneToMany
    private List<Comment> comments;


    @ManyToMany
    private List<Tag> tagList;


    @ManyToOne
    User user;
}
