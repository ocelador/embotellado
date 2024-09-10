package com.back.Back.controller;

import com.back.Back.model.Producto;
import com.back.Back.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Producto");
        producto.setCapacidad(500L);
    }

    @Test
    public void testGetAllProductos() {
        when(productoService.getAllProductos()).thenReturn(Arrays.asList(producto));

        List<Producto> productos = productoController.getAllProductos();
        assertEquals(1, productos.size());
        verify(productoService, times(1)).getAllProductos();
    }

    @Test
    public void testGetProductoById() {
        when(productoService.getProductoById(1L)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.getProductoById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(productoService, times(1)).getProductoById(1L);
    }

    @Test
    public void testGetProductoById_NotFound() {
        when(productoService.getProductoById(1L)).thenReturn(null);

        ResponseEntity<Producto> response = productoController.getProductoById(1L);
        assertEquals(404, response.getStatusCode().value());
        verify(productoService, times(1)).getProductoById(1L);
    }

    @Test
    public void testCreateProducto() {
        when(productoService.createProducto(producto)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.createProducto(producto);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(productoService, times(1)).createProducto(producto);
    }

    @Test
    public void testCreateProducto_Exception() {
        when(productoService.createProducto(producto)).thenThrow(new RuntimeException());

        ResponseEntity<Producto> response = productoController.createProducto(producto);
        assertEquals(500, response.getStatusCode().value());
        verify(productoService, times(1)).createProducto(producto);
    }

    @Test
    public void testUpdateProducto() {
        Producto updatedDetails = new Producto();
        updatedDetails.setNombre("Updated Name");
        when(productoService.updateProducto(1L, updatedDetails)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.updateProducto(1L, updatedDetails);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(productoService, times(1)).updateProducto(1L, updatedDetails);
    }

    @Test
    public void testUpdateProducto_NotFound() {
        Producto updatedDetails = new Producto();
        when(productoService.updateProducto(1L, updatedDetails)).thenReturn(null);

        ResponseEntity<Producto> response = productoController.updateProducto(1L, updatedDetails);
        assertEquals(404, response.getStatusCode().value());
        verify(productoService, times(1)).updateProducto(1L, updatedDetails);
    }

    @Test
    public void testUpdateProducto_Exception() {
        Producto updatedDetails = new Producto();
        when(productoService.updateProducto(1L, updatedDetails)).thenThrow(new RuntimeException());

        ResponseEntity<Producto> response = productoController.updateProducto(1L, updatedDetails);
        assertEquals(500, response.getStatusCode().value());
        verify(productoService, times(1)).updateProducto(1L, updatedDetails);
    }

    @Test
    public void testDeleteProducto() {
        when(productoService.deleteProducto(1L)).thenReturn(true);

        ResponseEntity<Void> response = productoController.deleteProducto(1L);
        assertEquals(204, response.getStatusCode().value());
        verify(productoService, times(1)).deleteProducto(1L);
    }

    @Test
    public void testDeleteProducto_NotFound() {
        when(productoService.deleteProducto(1L)).thenReturn(false);

        ResponseEntity<Void> response = productoController.deleteProducto(1L);
        assertEquals(404, response.getStatusCode().value());
        verify(productoService, times(1)).deleteProducto(1L);
    }

    @Test
    public void testDeleteProducto_Exception() {
        when(productoService.deleteProducto(1L)).thenThrow(new RuntimeException());

        ResponseEntity<Void> response = productoController.deleteProducto(1L);
        assertEquals(500, response.getStatusCode().value());
        verify(productoService, times(1)).deleteProducto(1L);
    }
}