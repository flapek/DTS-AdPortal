package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//@MappedSuperclass
@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected String title;
    protected float price;

    @ManyToMany
    protected List<Tag> tagList;
}
