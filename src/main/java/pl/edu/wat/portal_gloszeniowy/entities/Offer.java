package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.*;
import org.hibernate.annotations.Type;
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
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] photos;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<Tag> tagList;


    @ManyToOne
    User user;
}
