package com.proyect.backend.apirest.modelsDao;

import com.proyect.backend.apirest.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
    
}
