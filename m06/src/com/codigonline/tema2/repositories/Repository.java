package com.codigonline.tema2.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    T findOneById(int id);

    T save(T t);

    void updateById(int id, T t);

    void deleteById(int id);
}
