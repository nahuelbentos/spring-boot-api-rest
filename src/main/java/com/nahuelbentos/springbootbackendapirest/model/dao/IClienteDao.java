package com.nahuelbentos.springbootbackendapirest.model.dao;

import com.nahuelbentos.springbootbackendapirest.model.entity.Cliente;
import com.nahuelbentos.springbootbackendapirest.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
    @Query("from Region ")
    public List<Region> findAllRegiones();
}
