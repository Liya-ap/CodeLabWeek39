package daos;

import dtos.PoemDTO;
import entities.Poem;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PoemDao {
    private EntityManagerFactory emf;

    public PoemDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PoemDTO create(Context ctx) {
        PoemDTO poemDTO = ctx.bodyAsClass(PoemDTO.class);
        Poem poem = poemDTO.getPoemEntity();
        if (poem != null) {
            ctx.status(201); //CREATED
            ctx.json(poem);

            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(poem);
                em.getTransaction().commit();

                return new PoemDTO(poem);
            }
        } else {
            ctx.status(404); //COULD NOT CREATE
            ctx.result("Poem could not be created");
            throw new RuntimeException("Poem could not be created in database.");
        }
    }
}
