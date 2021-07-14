package com.proyect.backend.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no puede ser vacío")
    @Size(min = 4, max = 12, message = "el tamaño tiene que ser entre 4 y 12")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "no puede ser vacío")
    private String apellido;

    @NotEmpty(message = "no puede ser vacío")
    @Email(message = "no es una direccion de correo bien formada")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "no puede ser vacío")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private String foto;

    // LAZY = carga peresosa, cada vez que invoquemos el metodo o atributo, cuando se invoque es cuando se realiza la carga
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id") // llave foranea, el id en la tabla
    // LAZY generra un proxy hacia region que genera otros atributos adicionales propios de framework, para omitirlos se usa la anotacion @JsonIgnoreProperties
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Region region;
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    private static final long serialVersionUID = 1L;

}
