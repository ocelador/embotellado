package com.back.Back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "is_alcohólica", nullable = false)
    private Boolean isAlcohólica;

    @NotNull
    @Column(nullable = false)
    private Boolean is00;

    @NotNull
    @Column(nullable = false)
    private Boolean is0Azucar;

    @NotNull
    @Column(name = "is_natural", nullable = false)
    private Boolean isNatural;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    // Getters y Setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getIsAlcohólica() {
        return isAlcohólica;
    }

    public void setIsAlcohólica(Boolean isAlcohólica) {
        this.isAlcohólica = isAlcohólica;
    }

    public Boolean getIs00() {
        return is00;
    }

    public void setIs00(Boolean is00) {
        this.is00 = is00;
    }

    public Boolean getIs0Azucar() {
        return is0Azucar;
    }

    public void setIs0Azucar(Boolean is0Azucar) {
        this.is0Azucar = is0Azucar;
    }

    public Boolean getIsNatural() {
        return isNatural;
    }

    public void setIsNatural(Boolean isNatural) {
        this.isNatural = isNatural;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}