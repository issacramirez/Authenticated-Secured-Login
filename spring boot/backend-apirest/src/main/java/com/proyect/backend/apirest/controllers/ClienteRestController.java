package com.proyect.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.proyect.backend.apirest.models.Cliente;
import com.proyect.backend.apirest.modelsServices.ICLienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" }) // CORS
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private ICLienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    // @ResponseStatus(HttpStatus.OK) redundante ya que lo asigna por defecto
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = clienteService.findById(id);
        } catch (NoSuchElementException e) {
            response.put("Mensaje", "Error al realizar la consulta en la base de datos.");
            response.put("Error", "El cliente ID: ".concat(id.toString().concat(" no existe en la Base de Datos.")));
            response.put("Estatus", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // if(cliente == null){
        // response.put("Error", "El cliente ID:".concat(id.toString().concat(" no
        // existe en la Base de Datos.")));
        // return new ResponseEntity<Map<String, Object>>(response,
        // HttpStatus.NOT_FOUND);
        // }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            nuevoCliente = clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar el insert en la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            response.put("Estatus", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje", "El cliente se ha creado con éxito");
        response.put("Cliente", nuevoCliente);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    // @ResponseStatus(HttpStatus.CREATED) // return 201, indica que se ha creado
    // contenido
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
        Cliente nuevoCliente = null;
        Cliente clienteActual = null;
        Map<String, Object> response = new HashMap<>();

        try {
            clienteActual = clienteService.findById(id);
        } catch (Exception e) {
            response.put("Mensaje", "Error: no se pudo editar, El cliente ID: ".concat(id.toString().concat(" no existe en la Base de Datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt());

            nuevoCliente = clienteService.save(clienteActual);

        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al actualizar en la base de datos.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            response.put("Estatus", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje", "El cliente se ha actualizado con éxito");
        response.put("Cliente", nuevoCliente);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try{
            clienteService.delete(id);
        }catch(DataAccessException e){
            response.put("Mensaje", "Error al eliminar de la base de datos.");
            response.put("Error", e.getMessage());
            response.put("Estatus", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido eliminado con exito!");
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
