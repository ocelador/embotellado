package com.back.Back.service;

import com.back.Back.model.Producto;
import com.back.Back.reopsitory.ProductosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductosRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    public void testGetAllProductos() {
        Producto producto = new Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));

        List<Producto> productos = productoService.getAllProductos();
        assertEquals(1, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductoById() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto foundProducto = productoService.getProductoById(1L);
        assertNotNull(foundProducto);
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateProducto() {
        Producto producto = new Producto();
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto createdProducto = productoService.createProducto(producto);
        assertNotNull(createdProducto);
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    public void testUpdateProducto() {
        Producto producto = new Producto();
        producto.setCapacidad(500L); // Asegurarse de que la capacidad no sea nula
        Producto updatedDetails = new Producto();
        updatedDetails.setNombre("Updated Name");
        updatedDetails.setCapacidad(1000L); // Establecer un valor válido para capacidad
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(producto)).thenReturn(producto);
    
        Producto updatedProducto = productoService.updateProducto(1L, updatedDetails);
        assertNotNull(updatedProducto);
        assertEquals("Updated Name", updatedProducto.getNombre());
        assertEquals(1000L, updatedProducto.getCapacidad());
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    public void testDeleteProducto() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        doNothing().when(productoRepository).delete(producto);

        boolean isDeleted = productoService.deleteProducto(1L);
        assertTrue(isDeleted);
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).delete(producto);
    }
}