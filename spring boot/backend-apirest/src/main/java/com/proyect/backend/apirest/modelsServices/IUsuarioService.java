package com.proyect.backend.apirest.modelsServices;

import com.proyect.backend.apirest.models.Usuario;

public interface IUsuarioService {
    
    public Usuario findByUsername(String username);
}
