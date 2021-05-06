package com.nahuelbentos.springbootbackendapirest.model.services;

import com.nahuelbentos.springbootbackendapirest.model.entity.Cliente;
import com.nahuelbentos.springbootbackendapirest.model.entity.Factura;
import com.nahuelbentos.springbootbackendapirest.model.entity.Producto;
import com.nahuelbentos.springbootbackendapirest.model.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

     Cliente findById(Long id);

    Cliente save( Cliente cliente );

    void delete(Long id);

    List<Region> findAllRegiones();

    Cliente update( Long id, Cliente cliente );

    Factura findFacturaById( Long id );

    Factura saveFactura( Factura factura );

    void deleteFacturaById( Long id );

    List<Producto> findProductoByNombre( String nombre);
}
