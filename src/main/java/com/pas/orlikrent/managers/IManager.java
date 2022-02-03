package com.pas.orlikrent.managers;

import com.pas.orlikrent.exceptions.Base_Exception;

import java.util.List;

public interface IManager<T, M> {
    List<T> getAll();

    T getByID(M id) throws Base_Exception;

    void add(T o) throws Base_Exception;

    void remove(M id, T o) throws Base_Exception;

    void update(M id, T o) throws Base_Exception;
}
