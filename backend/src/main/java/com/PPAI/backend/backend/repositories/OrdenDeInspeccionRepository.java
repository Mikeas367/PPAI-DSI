package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.OrdenDeInspeccion;
import jakarta.persistence.EntityManager;

public class OrdenDeInspeccionRepository extends RepositorioBase<OrdenDeInspeccion> {
    public OrdenDeInspeccionRepository(EntityManager em) {
        super(em, OrdenDeInspeccion.class);
    }
}
