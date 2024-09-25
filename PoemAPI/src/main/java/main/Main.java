package main;

import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}