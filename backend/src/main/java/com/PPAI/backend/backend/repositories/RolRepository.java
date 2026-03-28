package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.Rol;
import jakarta.persistence.EntityManager;

public class RolRepository extends RepositorioBase<Rol>{

    public RolRepository(EntityManager em) {
        super(em, Rol.class);
    }



}
