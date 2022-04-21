package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private String user;

    @ManyToOne
    private Offer offer;
}
