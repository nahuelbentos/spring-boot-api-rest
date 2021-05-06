package com.nahuelbentos.springbootbackendapirest.model.services;

import com.nahuelbentos.springbootbackendapirest.model.dao.IClienteDao;
import com.nahuelbentos.springbootbackendapirest.model.dao.IFacturaDao;
import com.nahuelbentos.springbootbackendapirest.model.dao.IProductoDao;
import com.nahuelbentos.springbootbackendapirest.model.entity.Cliente;
import com.nahuelbentos.springbootbackendapirest.model.entity.Factura;
import com.nahuelbentos.springbootbackendapirest.model.entity.Producto;
import com.nahuelbentos.springbootbackendapirest.model.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    public final IFacturaDao facturaDao;

    private final IClienteDao clienteDao;

    private final IProductoDao productoDao;

    public ClienteService(IClienteDao clienteDao, IFacturaDao facturaDao, IProductoDao productoDao) {
        this.clienteDao = clienteDao;
        this.facturaDao = facturaDao;
        this.productoDao = productoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteDao.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Cliente id %d no existe.", id)));
    }

    @Override
    @Transactional
    public Cliente update(Long id, Cliente cliente) {
        Optional<Cliente> clienteActual = clienteDao.findById(id);
        if (clienteActual.isPresent()) {
            clienteActual.get().setNombre(cliente.getNombre());
            clienteActual.get().setApellido(cliente.getApellido());
            clienteActual.get().setEmail(cliente.getEmail());
            clienteActual.get().setCreateAt(cliente.getCreateAt());
            clienteActual.get().setRegion(cliente.getRegion());
            return clienteDao.save(clienteActual.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Cliente id %d no existe", id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return facturaDao.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Factura id %d no existe.", id)));
    }

    @Override
    @Transactional
    public Factura saveFactura(Factura factura) {
        return facturaDao.save(factura);
    }

    @Override
    @Transactional
    public void deleteFacturaById(Long id) {
        facturaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findProductoByNombre(String nombre) {
        return productoDao.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones() {
        return clienteDao.findAllRegiones();
    }
}
