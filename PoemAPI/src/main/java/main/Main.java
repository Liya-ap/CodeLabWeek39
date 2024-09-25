package main;

import config.HibernateConfig;
import daos.PoemDao;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");
    private static PoemDao poemDao = new PoemDao(emf);

    public static void main(String[] args) {
        Javalin app = Javalin.create((config) -> {
            config.router.contextPath = "/api/poems";
            config.bundledPlugins.enableRouteOverview("/routes");
        }).start(7007);

        app.post("/poem", ctx -> poemDao.create(ctx));
    }
}