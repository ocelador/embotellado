package com.back.Back.controller;

import com.back.Back.model.Producto;
import com.back.Back.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto) {
        try {
            Producto createdProducto = productoService.createProducto(producto);
            return ResponseEntity.ok(createdProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @Valid @RequestBody Producto productoDetails) {
        try {
            Producto updatedProducto = productoService.updateProducto(id, productoDetails);
            if (updatedProducto != null) {
                return ResponseEntity.ok(updatedProducto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        try {
            boolean deleted = productoService.deleteProducto(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }
}