package com.proyect.backend.apirest.modelsServices;

import java.util.List;

import com.proyect.backend.apirest.models.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICLienteService {
    
    public List<Cliente> findAll();
    public Page<Cliente> findAll(Pageable pageable);
    public Cliente findById(Long id);
    public Cliente save(Cliente cliente);
    public void delete(Long id);

}
