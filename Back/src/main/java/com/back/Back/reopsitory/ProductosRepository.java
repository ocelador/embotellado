package com.back.Back.reopsitory;

import com.back.Back.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {
}
