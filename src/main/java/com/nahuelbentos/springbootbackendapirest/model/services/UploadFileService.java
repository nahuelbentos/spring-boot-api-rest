package com.nahuelbentos.springbootbackendapirest.model.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class UploadFileService implements IUploadFileService {
    private final static String DIRECTORIO_UPLOAD = "uploads";
    private final Logger logger = LoggerFactory.getLogger(UploadFileService.class);

    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreFoto);

        logger.info(rutaArchivo.toString());
        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() && !recurso.isReadable()) {
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
            recurso = new UrlResource(rutaArchivo.toUri());
            logger.error("Error no se pudo cargar la imagen: " + nombreFoto);
        }
        return recurso;
    }

    @Override
    public String copiar(MultipartFile archivo) throws IOError, IOException {
        String nombreArchivo = UUID.randomUUID().toString() + "_" + Objects.requireNonNull(archivo.getOriginalFilename()).replace(" ", "-");
        Path rutaArchiva = getPath(nombreArchivo);
        logger.info(rutaArchiva.toString());
        Files.copy(archivo.getInputStream(), rutaArchiva);
        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String nombreFoto) {

        if (nombreFoto != null && nombreFoto.length() > 0) {
            Path rutaFotoAnterior = getPath(nombreFoto);
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }
}
