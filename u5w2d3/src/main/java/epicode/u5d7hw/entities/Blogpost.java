package epicode.u5d7hw.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "blogposts")
public class Blogpost {
    @Id
    @GeneratedValue
    private Long id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
