package com.back.Back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Producto {
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
    private String marca;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String sabor;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String envase;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String descripcion;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Long capacidad;

    @ManyToOne
    @JsonBackReference
    private Categoria categoria;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getEnvase() {
        return envase;
    }

    public void setEnvase(String envase) {
        this.envase = envase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Long capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad no puede ser ni 0 ni negativa");
        }
        this.capacidad = capacidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}