package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Base_Exception;

import java.util.List;

public interface IRepository<T, M> {
    List<T> getAll();

    T getByID(M id) throws Base_Exception;

    void add(T o) throws Base_Exception;

    void remove(T o) throws Base_Exception;

    void update(M id, T o) throws Base_Exception;

}
