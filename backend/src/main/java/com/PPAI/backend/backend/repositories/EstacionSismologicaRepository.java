package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.EstacionSismologica;
import jakarta.persistence.EntityManager;

public class EstacionSismologicaRepository extends RepositorioBase<EstacionSismologica> {
    public EstacionSismologicaRepository(EntityManager em) {
        super(em, EstacionSismologica.class);
    }
}
