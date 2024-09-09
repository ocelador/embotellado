package com.back.Back.service;

import com.back.Back.model.Categoria;
import com.back.Back.reopsitory.CategoriaRepository;
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
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    public void testGetAllCategorias() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));

        List<Categoria> categorias = categoriaService.getAllCategorias();
        assertEquals(1, categorias.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    public void testGetCategoriaById() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria foundCategoria = categoriaService.getCategoriaById(1L);
        assertNotNull(foundCategoria);
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria createdCategoria = categoriaService.createCategoria(categoria);
        assertNotNull(createdCategoria);
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    public void testUpdateCategoria() {
        Categoria categoria = new Categoria();
        Categoria updatedDetails = new Categoria();
        updatedDetails.setNombre("Updated Name");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria updatedCategoria = categoriaService.updateCategoria(1L, updatedDetails);
        assertNotNull(updatedCategoria);
        assertEquals("Updated Name", updatedCategoria.getNombre());
        verify(categoriaRepository, times(1)).findById(1L);
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    public void testDeleteCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        doNothing().when(categoriaRepository).delete(categoria);

        boolean isDeleted = categoriaService.deleteCategoria(1L);
        assertTrue(isDeleted);
        verify(categoriaRepository, times(1)).findById(1L);
        verify(categoriaRepository, times(1)).delete(categoria);
    }
}