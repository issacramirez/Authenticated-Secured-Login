package com.proyect.backend.apirest.modelsServices;

import java.util.List;

import com.proyect.backend.apirest.models.Cliente;

public interface ICLienteService {
    
    public List<Cliente> findAll();
    public Cliente findById(Long id);
    public Cliente save(Cliente cliente);
    public void delete(Long id);

}
