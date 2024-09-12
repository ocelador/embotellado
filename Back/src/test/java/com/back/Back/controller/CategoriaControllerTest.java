package com.back.Back.controller;

import com.back.Back.model.Categoria;
import com.back.Back.service.CategoriaService;
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
public class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Test Categoria");
    }

    @Test
    public void testGetAllCategorias() {
        when(categoriaService.getAllCategorias()).thenReturn(Arrays.asList(categoria));

        List<Categoria> categorias = categoriaController.getAllCategorias();
        assertEquals(1, categorias.size());
        verify(categoriaService, times(1)).getAllCategorias();
    }

    @Test
    public void testGetCategoriaById() {
        when(categoriaService.getCategoriaById(1L)).thenReturn(categoria);

        ResponseEntity<Categoria> response = categoriaController.getCategoriaById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(categoriaService, times(1)).getCategoriaById(1L);
    }

    @Test
    public void testGetCategoriaById_NotFound() {
        when(categoriaService.getCategoriaById(1L)).thenReturn(null);

        ResponseEntity<Categoria> response = categoriaController.getCategoriaById(1L);
        assertEquals(404, response.getStatusCode().value());
        verify(categoriaService, times(1)).getCategoriaById(1L);
    }

    @Test
    public void testCreateCategoria() {
        when(categoriaService.createCategoria(categoria)).thenReturn(categoria);

        Categoria createdCategoria = categoriaController.createCategoria(categoria);
        assertNotNull(createdCategoria);
        verify(categoriaService, times(1)).createCategoria(categoria);
    }

    @Test
    public void testUpdateCategoria() {
        Categoria updatedDetails = new Categoria();
        updatedDetails.setNombre("Updated Name");
        when(categoriaService.updateCategoria(1L, updatedDetails)).thenReturn(categoria);

        ResponseEntity<Categoria> response = categoriaController.updateCategoria(1L, updatedDetails);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(categoriaService, times(1)).updateCategoria(1L, updatedDetails);
    }

    @Test
    public void testUpdateCategoria_NotFound() {
        Categoria updatedDetails = new Categoria();
        when(categoriaService.updateCategoria(1L, updatedDetails)).thenReturn(null);

        ResponseEntity<Categoria> response = categoriaController.updateCategoria(1L, updatedDetails);
        assertEquals(404, response.getStatusCode().value());
        verify(categoriaService, times(1)).updateCategoria(1L, updatedDetails);
    }

    @Test
    public void testDeleteCategoria() {
        when(categoriaService.deleteCategoria(1L)).thenReturn(true);
    
        ResponseEntity<String> response = categoriaController.deleteCategoria(1L);
        assertEquals(204, response.getStatusCode().value());
        verify(categoriaService, times(1)).deleteCategoria(1L);
    }

    @Test
    public void testDeleteCategoria_Conflict() {
        when(categoriaService.deleteCategoria(1L)).thenReturn(false);

        ResponseEntity<String> response = categoriaController.deleteCategoria(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("No se puede eliminar la categoría porque tiene productos asociados.", response.getBody());
        verify(categoriaService, times(1)).deleteCategoria(1L);
    }
}