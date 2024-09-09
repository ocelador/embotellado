package com.back.Back.service;

import com.back.Back.model.Producto;
import com.back.Back.reopsitory.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductosRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    public Producto createProducto(Producto producto) {
        try {
            return productoRepository.save(producto);
        } catch (DataAccessException e) {
            // Manejo de excepciones de acceso a datos
            throw new RuntimeException("Error al crear el producto", e);
        }
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setNombre(productoDetails.getNombre());
            producto.setMarca(productoDetails.getMarca());
            producto.setSabor(productoDetails.getSabor());
            producto.setEnvase(productoDetails.getEnvase());
            producto.setDescripcion(productoDetails.getDescripcion());
            producto.setCapacidad(productoDetails.getCapacidad());
            producto.setCategoria(productoDetails.getCategoria());
            try {
                return productoRepository.save(producto);
            } catch (DataAccessException e) {
                // Manejo de excepciones de acceso a datos
                throw new RuntimeException("Error al actualizar el producto", e);
            }
        } else {
            return null;
        }
    }

    public boolean deleteProducto(Long id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            try {
                productoRepository.delete(optionalProducto.get());
                return true;
            } catch (DataAccessException e) {
                // Manejo de excepciones de acceso a datos
                throw new RuntimeException("Error al eliminar el producto", e);
            }
        } else {
            return false;
        }
    }
}