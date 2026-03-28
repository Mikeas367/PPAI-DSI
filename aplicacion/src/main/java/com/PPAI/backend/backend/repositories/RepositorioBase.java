package com.PPAI.backend.backend.repositories;

import com.PPAI.backend.backend.interfaces.IRepository;
import jakarta.persistence.EntityManager;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class RepositorioBase<T> {
    protected EntityManager em;
    private Class<T> clazz;

    public RepositorioBase(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    public void crear(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public T obtenerPorId(int id) {
        return em.find(clazz, id);
    }

    public List<T> obtenerTodos() {
        return em.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }

    public void actualizar(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void eliminarPorId(int id) {
        T entity = em.find(clazz, id);
        if (entity != null) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }
    }
}