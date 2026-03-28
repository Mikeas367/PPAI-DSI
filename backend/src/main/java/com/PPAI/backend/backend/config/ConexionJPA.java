package com.PPAI.backend.backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConexionJPA {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("SismografoPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void cerrar() {
        emf.close();
    }
}
