package com.proyect.backend.apirest.modelsDao;

import com.proyect.backend.apirest.models.Usuario;

// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    
    public Usuario findByUsername(String username);

    //@Query("select u from Usuario u where u.username=?1 and u.otro=?2")
    //public Usuario findByUsername2(String username, String email);

}
