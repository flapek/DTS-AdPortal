package pl.edu.wat.portal_gloszeniowy.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private String username;

    @ManyToOne
    private Offer offer;
}
