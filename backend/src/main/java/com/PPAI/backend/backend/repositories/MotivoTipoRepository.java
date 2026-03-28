package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.MotivoTipo;
import jakarta.persistence.EntityManager;

public class MotivoTipoRepository extends RepositorioBase<MotivoTipo> {

    public MotivoTipoRepository(EntityManager em) {
        super(em, MotivoTipo.class);
    }

}
