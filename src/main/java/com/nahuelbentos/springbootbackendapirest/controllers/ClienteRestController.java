package com.nahuelbentos.springbootbackendapirest.controllers;

import com.nahuelbentos.springbootbackendapirest.model.entity.Cliente;
import com.nahuelbentos.springbootbackendapirest.model.entity.Region;
import com.nahuelbentos.springbootbackendapirest.model.services.IClienteService;
import com.nahuelbentos.springbootbackendapirest.model.services.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    private final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);
    private final IClienteService clienteService;

    private final IUploadFileService uploadFileService;

    public ClienteRestController(IClienteService clienteService, IUploadFileService uploadFileService) {
        this.clienteService = clienteService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getClientes() {
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/clientes/page/{page}")
    public ResponseEntity<Page<Cliente>> getClientes(@PathVariable Integer page) {
        return new ResponseEntity<>(clienteService.findAll(PageRequest.of(page, 4)), HttpStatus.OK);

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
    }

    @Secured( "ROLE_ADMIN" )
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        ResponseEntity<?> response = validateClient(result);
        if (response != null) return response;
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @Secured( "ROLE_ADMIN" )
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

        ResponseEntity<?> response = validateClient(result);
        if (response != null) return response;
        return new ResponseEntity<>(clienteService.update(id, cliente), HttpStatus.OK);
    }

    private ResponseEntity<?> validateClient(BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo: '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
        return null;
    }

    @Secured( "ROLE_ADMIN" )
    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Cliente cliente = clienteService.findById(id);

        String fotoAnterior = cliente.getFoto();
        uploadFileService.eliminar(fotoAnterior);
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteService.findById(id);
        if (!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadFileService.copiar(archivo);
            } catch (IOException e) {
                e.printStackTrace();
                response.put("mensaje", "Error al subir la imagen: " + nombreArchivo);
                response.put("Errors", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String fotoAnterior = cliente.getFoto();
            uploadFileService.eliminar(fotoAnterior);

            cliente.setFoto(nombreArchivo);
            clienteService.save(cliente);
            response.put("cliente", cliente);
            response.put("mensaje", "Has subido correctamentess la fotos de perfil: " + nombreArchivo);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/clientes/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

        Resource recurso = null;
        try {
            recurso = uploadFileService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<>(recurso, headers, HttpStatus.OK);
    }


    @Secured( "ROLE_ADMIN" )
    @GetMapping("/clientes/regiones")
    public ResponseEntity<List<Region>> getRegiones() {
        return new ResponseEntity<>(clienteService.findAllRegiones(), HttpStatus.OK);

    }

}
