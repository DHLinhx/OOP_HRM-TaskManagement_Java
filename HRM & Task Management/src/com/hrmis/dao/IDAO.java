package com.hrmis.dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T, ID> {
    List<T> getAll();
    Optional<T> getById(ID id);
    void add(T entity);
    void update(T entity);
    boolean delete(ID id);
}
