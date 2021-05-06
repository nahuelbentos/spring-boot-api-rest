package com.nahuelbentos.springbootbackendapirest.controllers;

import com.nahuelbentos.springbootbackendapirest.model.entity.Factura;
import com.nahuelbentos.springbootbackendapirest.model.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin( origins = {"http://localhost:4200", "http://localhost:8100", "*"})
@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {
    private final IClienteService clienteService;

    public FacturaRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity<>(clienteService.findFacturaById(id), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        clienteService.deleteFacturaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Factura factura){
        return new ResponseEntity<>(clienteService.saveFactura(factura), HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/filtrar-productos/{filtro}")
    public ResponseEntity<?> filtrarProductos( @PathVariable String filtro){
        return new ResponseEntity<>(clienteService.findProductoByNombre( filtro ), HttpStatus.OK);
    }
}
