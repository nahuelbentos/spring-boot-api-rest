package com.nahuelbentos.springbootbackendapirest.model.dao;

import com.nahuelbentos.springbootbackendapirest.model.entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long> {
}
