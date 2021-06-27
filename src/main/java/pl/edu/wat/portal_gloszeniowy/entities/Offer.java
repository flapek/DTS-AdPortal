package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.Data;
import pl.edu.wat.portal_gloszeniowy.models.User;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private float price;
    private String detais;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photos;

    @OneToMany
    private List<Comment> comments;


    @ManyToMany
    private List<Tag> tagList;


    @ManyToOne
    User user;
}
