package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tag")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tagList")
    List<Offer> offerList = new LinkedList<>();
}
