package entities;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Poem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Type type;
    @Column(nullable = false, length = 1000)
    private String poem;
    private String author;

    public Poem(String title, Type type, String poem, String author) {
        this.title = title;
        this.type = type;
        this.poem = poem;
        this.author = author;
    }
}
