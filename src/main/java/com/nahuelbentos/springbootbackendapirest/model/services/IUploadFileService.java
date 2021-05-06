package com.nahuelbentos.springbootbackendapirest.model.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadFileService {
    public Resource cargar(String nombreFoto) throws MalformedURLException;
    public String copiar(MultipartFile archivo) throws IOError, IOException;
    public boolean eliminar(String nombre);
    public Path getPath(String nombre);
}
