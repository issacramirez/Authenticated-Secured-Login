package com.proyect.backend.apirest.modelsServices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import com.proyect.backend.apirest.models.Cliente;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
    
    public Resource cargar(String nombreFoto) throws MalformedURLException;
    public String copiar(MultipartFile archivo, Cliente cliente) throws IOException;
    public boolean eliminar(String nombreFoto);
    public Path getPath(String nombreFoto);
    
}
