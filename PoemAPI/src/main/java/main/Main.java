package main;

import config.HibernateConfig;
import daos.PoemDao;
import dtos.PoemDTO;
import entities.PoemsList;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");
    private static PoemDao poemDao = new PoemDao(emf);


    public static void main(String[] args) {
        PoemsList list = new PoemsList();

        Javalin app = Javalin.create((config) -> {
            config.router.contextPath = "/api/poems";
            config.bundledPlugins.enableRouteOverview("/routes");
        });

        list.getPoemsDTOs().forEach(l -> poemDao.createPoemDTO(l));

        app.post("/poem", ctx -> poemDao.create(ctx));

        app.get("/", ctx -> poemDao.getAllPoems(ctx));

        app.start(7007);
    }
}