package daos;

import dtos.PoemDTO;
import entities.Poem;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;

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
            throw new RuntimeException("Poem could not be created in database from API.");
        }
    }

    public PoemDTO createPoemDTO(PoemDTO poemDTO) {
        Poem poem = poemDTO.getPoemEntity();
        if (poem != null) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(poem);
                em.getTransaction().commit();

                return new PoemDTO(poem);
            }
        } else {
            throw new RuntimeException("Poem could not be created in database.");
        }
    }

    public Set<PoemDTO> getAllPoems(Context ctx) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Poem> query = em.createQuery("SELECT p FROM Poem p", Poem.class);
            Set<PoemDTO> poemDTOS = query.getResultStream().map(PoemDTO::new).collect(Collectors.toSet());

            if (!poemDTOS.isEmpty()) {
                ctx.status(200);
                ctx.json(poemDTOS);

                return poemDTOS;
            } else {
                ctx.status(404); //NOT FOUND
                ctx.result("No poems found.");
                throw new RuntimeException("Could not get any poems from database.");
            }
        }
    }
}
