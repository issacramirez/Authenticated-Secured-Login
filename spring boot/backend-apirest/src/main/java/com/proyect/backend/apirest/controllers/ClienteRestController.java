package com.proyect.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.proyect.backend.apirest.models.Cliente;
import com.proyect.backend.apirest.modelsServices.ICLienteService;
import com.proyect.backend.apirest.modelsServices.IUploadFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = { "http://localhost:4200" }) // CORS
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private ICLienteService clienteService;
    
    @Autowired
    private IUploadFileService uploadFileService;

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return clienteService.findAll(pageable);
    }

    @GetMapping("/clientes/{id}")
    // @ResponseStatus(HttpStatus.OK) redundante ya que lo asigna por defecto
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = clienteService.findById(id);
        } catch (Exception e) {
            if (cliente == null) {
                response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la Base de Datos.")));
                response.put("error", "Exception: ".concat(e.getClass().getSimpleName()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            response.put("mensaje", "Error al realizar la consulta en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getCause().toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente nuevoCliente = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors() ){
            List<String> errors = result.getFieldErrors()
                            .stream()
                            .map(e -> "El campo: '" + e.getField() + "' " + e.getDefaultMessage())
                            .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            nuevoCliente = clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("error1", "Exception: ".concat(e.getClass().getSimpleName()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se ha creado con éxito");
        response.put("cliente", nuevoCliente);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    // @ResponseStatus(HttpStatus.CREATED) // return 201, indica que se ha creado contenido
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
        Cliente nuevoCliente = null;
        Cliente clienteActual = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream().map(e -> "El campo: '" + e.getField() + "' tiene error: " + e.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            clienteActual = clienteService.findById(id);
        } catch (Exception e) {
            response.put("mensaje", "Error: no se pudo editar, El cliente ID: ".concat(id.toString().concat(" no existe en la Base de Datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt());
            clienteActual.setRegion(cliente.getRegion());

            nuevoCliente = clienteService.save(clienteActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos.");
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("error1", "Exception: ".concat(e.getClass().getSimpleName()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se ha actualizado con éxito");
        response.put("cliente", nuevoCliente);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try{
            Cliente cliente = clienteService.findById(id);
            String fotoAnterior = cliente.getFoto();
            uploadFileService.eliminar(fotoAnterior);
            clienteService.delete(id);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar de la base de datos.");
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("error1", "Exception: ".concat(e.getClass().getSimpleName()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido eliminado con exito!");
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        Cliente cliente = clienteService.findById(id);
        if(!archivo.isEmpty()){
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadFileService.copiar(archivo, cliente);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen a la base de datos.");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                response.put("error1", "Exception: ".concat(e.getClass().getSimpleName()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String fotoAnterior = cliente.getFoto();
            uploadFileService.eliminar(fotoAnterior);
            cliente.setFoto(nombreArchivo);
            clienteService.save(cliente);
            response.put("cliente", cliente);
            response.put("mensaje", "haz subido correctamente la imagen: " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // metodo handler para ver imagen en el controlador
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso = null;
        try {
            recurso = uploadFileService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error("Error", "exception: " + e.getMessage());
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

}
