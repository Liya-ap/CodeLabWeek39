package dtos;

import entities.Poem;
import entities.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoemDTO {
    private String title;
    private Type type;
    private String poem;
    private String author;

    public PoemDTO(Poem poem) {
        this.title = poem.getTitle();
        this.type = poem.getType();
        this.poem = poem.getPoem();
        this.author = poem.getAuthor();
    }

    public Poem getPoemEntity(){
        return new Poem(title, type, poem, author);
    }
}
