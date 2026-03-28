package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.Estado;
import jakarta.persistence.EntityManager;

public class EstadoRepository extends RepositorioBase<Estado> {
    public EstadoRepository(EntityManager em) {
        super(em, Estado.class);
    }
}
