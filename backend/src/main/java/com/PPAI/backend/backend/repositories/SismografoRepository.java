package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.Sismografo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class SismografoRepository extends RepositorioBase<Sismografo>{

    public SismografoRepository(EntityManager em) {
        super(em, Sismografo.class);
    }

    public List<Sismografo> getAll() {
        TypedQuery<Sismografo> query = em.createQuery("SELECT s FROM Sismografo s", Sismografo.class);
        return query.getResultList();
    }

}
