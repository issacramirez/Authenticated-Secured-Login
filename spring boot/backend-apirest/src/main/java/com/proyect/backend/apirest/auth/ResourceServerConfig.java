package com.proyect.backend.apirest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // reglas del lado de OAuth
    // nos permite implementar todas las reglas de seguridad de nuestros endpoints hacia los recursos de la aplicacion
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/regiones").permitAll() // permite las rutas del metodo
                                .anyRequest().authenticated() // cualquier otra ruta se requiere autenticación
        ;
    }
    
}
