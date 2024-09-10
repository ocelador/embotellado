package com.back.Back.service;

import com.back.Back.model.Categoria;
import com.back.Back.reopsitory.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoriaDetails) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre(categoriaDetails.getNombre());
            categoria.setDescripcion(categoriaDetails.getDescripcion());
            categoria.setIsAlcohólica(categoriaDetails.getIsAlcohólica());
            categoria.setIs00(categoriaDetails.getIs00());
            categoria.setIs0Azucar(categoriaDetails.getIs0Azucar());
            categoria.setIsNatural(categoriaDetails.getIsNatural());
            return categoriaRepository.save(categoria);
        } else {
            return null;
        }
    }

    public boolean deleteCategoria(Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
                // No se puede borrar la categoría porque tiene productos asociados
                return false;
            }
            categoriaRepository.delete(categoria);
            return true;
        } else {
            return false;
        }
    }
}