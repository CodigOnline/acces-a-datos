package com.codigonline.hb.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T findOneById(int id);
    void save(T t);
    void updateById(int id, T t);
    void deleteById(int id);
}
