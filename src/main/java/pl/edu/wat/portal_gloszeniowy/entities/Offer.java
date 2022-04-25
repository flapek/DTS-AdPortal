package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.Data;
import pl.edu.wat.portal_gloszeniowy.models.User;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offer")
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private float price;
    private String details;
    private Date date;
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
