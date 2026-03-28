package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.models.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EmpleadoRepository extends RepositorioBase<Empleado> {

    public EmpleadoRepository(EntityManager em) {
        super(em, Empleado.class);
    }


    public Empleado obtenerPorId(int id) {
        return em.find(Empleado.class, id);
    }


    public Empleado obtenerPorMail(String mail) {
        TypedQuery<Empleado> query = em.createQuery(
                "SELECT e FROM Empleado e WHERE e.mail = :mail", Empleado.class
        );
        query.setParameter("mail", mail);
        return query.getResultStream().findFirst().orElse(null);
    }
}