package com.PPAI.backend.backend.interfaces;

import java.util.List;

public interface IRepository<T> {
    void save(T entity);
    List<T> getAll();
    void deleteById(int id);
    T getById(int id);
    void update(T entity);
}