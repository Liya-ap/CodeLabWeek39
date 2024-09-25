package dtos;

import entities.Poem;
import entities.Type;
import lombok.Data;

@Data
public class PoemDTO {
    private long id;
    private String title;
    private Type type;
    private String poem;
    private String author;


    public PoemDTO(Poem poem) {
        this.id = poem.getId();
        this.title = poem.getTitle();
        this.type = poem.getType();
        this.poem = poem.getPoem();
        this.author = poem.getAuthor();
    }

    public Poem getPoemEntity(){
        return new Poem(id, title, type, poem, author);
    }
}
