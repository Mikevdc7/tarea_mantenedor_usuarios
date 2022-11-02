package org.mvallesg.mantenedorusuarios.jdbc.repositorio;

import java.util.List;

public interface Repositorio<T> {

    List<T> listar();
    T consultar(Long id);
    void insertar(T t);
    void actualizar(T t);
    void eliminar(Long id);
}