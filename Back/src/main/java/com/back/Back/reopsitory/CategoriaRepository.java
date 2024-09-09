package com.back.Back.reopsitory;

import com.back.Back.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c.nombre FROM Categoria c WHERE c.id = :id")
    String findNombreById(Long id);
}