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
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/regiones", "/api/clientes/page/**", "/api/uploads/img/**").permitAll() // permite las rutas del metodo
                                .antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
                                .antMatchers("/api/clientes/**").hasRole("ADMIN") // indica que cualquier otra ruta se necesita role ADMIN
                                .anyRequest().authenticated() // cualquier otra ruta se requiere autenticación
        ;
    }
    
}
