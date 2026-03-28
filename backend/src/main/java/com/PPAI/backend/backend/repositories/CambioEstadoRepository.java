package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.CambioEstado;
import jakarta.persistence.EntityManager;
import org.springframework.data.repository.support.Repositories;

public class CambioEstadoRepository extends RepositorioBase<CambioEstado> {
    public CambioEstadoRepository(EntityManager entityManager) {
        super(entityManager, CambioEstado.class);
    }
}
