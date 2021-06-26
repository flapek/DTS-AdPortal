package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;



    @ManyToMany
    List<Offer> offerList;
}
