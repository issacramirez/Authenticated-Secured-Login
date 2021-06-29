package com.proyect.backend.apirest.modelsDao;

import com.proyect.backend.apirest.models.Cliente;

import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {
    
}
