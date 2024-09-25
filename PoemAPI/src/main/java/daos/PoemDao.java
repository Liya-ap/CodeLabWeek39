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

public PoemDTO getPoemById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try (EntityManager em = emf.createEntityManager()) {
            Poem poem = em.find(Poem.class, id);

            if (poem != null) {
                ctx.status(200);
                ctx.json(poem);
                return new PoemDTO(poem);
            } else {
                ctx.status(404);
                ctx.result("No poem found with id " + id);
                throw new RuntimeException("Could not get poem with id " + id);
            }
        }
}

public PoemDTO deletePoem(Context ctx) {
    try (EntityManager em = emf.createEntityManager()) {
        em.getTransaction().begin();
        Poem poem = em.find(Poem.class, ctx.pathParam("id"));
        if (poem != null) {
            em.remove(poem);
            em.getTransaction().commit();
            ctx.status(204);
            return new PoemDTO(poem);
        } else {
            ctx.status(404);
            ctx.result("No poem found with id " + ctx.pathParam("id"));
            throw new RuntimeException("Could not delete poem with id " + ctx.pathParam("id"));
        }
    }
}

public PoemDTO getPoemById(Long id){
        try (EntityManager em = emf.createEntityManager()) {
            Poem poem = em.find(Poem.class, id);
            if (poem != null) {
                return new PoemDTO(poem);
            } else {
                return null;
            }
        }
}
public PoemDTO updatePoem(Context ctx) {
     Long id = Long.parseLong(ctx.pathParam("id"));
     PoemDTO poemDTO = getPoemById(id);
     Poem poem = poemDTO.getPoemEntity();

     if(poem != null){
         try (EntityManager em = emf.createEntityManager()) {
             em.getTransaction().begin();
             Poem foundPoem = em.find(Poem.class, id);

             if(poem.getTitle() != null){
                 foundPoem.setTitle(poem.getTitle());
             }
             if (poem.getType() != null){
                 foundPoem.setType(poem.getType());
             }
             if (poem.getPoem() != null){
                 foundPoem.setPoem(poem.getPoem());
             }
             if (poem.getAuthor() != null){
                 foundPoem.setAuthor(poem.getAuthor());
             }
             em.getTransaction().commit();
             return new PoemDTO(foundPoem);
         }
     } else {
         throw new RuntimeException("Poem could not be updated");
     }
 }
}
