package com.proyect.backend.apirest.modelsDao;

import com.proyect.backend.apirest.models.Region;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegionDao extends JpaRepository<Region, Long> {
    
}
