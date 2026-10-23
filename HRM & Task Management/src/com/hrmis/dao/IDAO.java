package com.hrmis.dao;

import java.util.List;
import java.util.Optional;

/**
 * Giao diện cơ sở định nghĩa các thao tác truy xuất dữ liệu chung (CRUD)
 * @param <T> Kiểu thực thể dữ liệu (Model)
 * @param <ID> Kiểu khóa chính (PK)
 */
public interface IDAO<T, ID> {
    List<T> getAll();
    Optional<T> getById(ID id);
    void add(T entity);
    void update(T entity);
    boolean delete(ID id);
}
