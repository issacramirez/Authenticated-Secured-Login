package com.proyect.backend.apirest.controllers;

import java.util.List;

import com.proyect.backend.apirest.models.Region;
import com.proyect.backend.apirest.modelsServices.IRegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" }) // CORS
@RestController
@RequestMapping("/api")
public class RegionRestController {

    @Autowired
    private IRegionService regionService;

    @GetMapping("/regiones")
    public List<Region> index() {
        return regionService.findAll();
    }
    
}
