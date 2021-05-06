package com.nahuelbentos.springbootbackendapirest.model.dao;

import com.nahuelbentos.springbootbackendapirest.model.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDao extends CrudRepository<Producto, Long> {

    @Query("select p from Producto  p where p.nombre like %?1%")
    List<Producto> findByNombre(String nombre);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByNombreStartingWithIgnoreCase(String nombre);

}
