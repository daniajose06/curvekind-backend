package com.curvekind.backend.repository;

import com.curvekind.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrueOrderByIdAsc();

    @Query("""
        SELECT p FROM Product p
        WHERE p.active = true
          AND (:category IS NULL OR p.category = :category)
        ORDER BY p.id
    """)
    List<Product> findActiveByCategory(@Param("category") String category);

    @Query("""
        SELECT DISTINCT p FROM Product p
        JOIN ProductBodyShape pbs ON pbs.productId = p.id
        JOIN BodyShape bs ON bs.id = pbs.bodyShapeId
        WHERE p.active = true
          AND bs.code = :bodyShapeCode
          AND (:category IS NULL OR p.category = :category)
        ORDER BY p.id
    """)
    List<Product> findActiveByBodyShape(@Param("bodyShapeCode") String bodyShapeCode,
                                        @Param("category") String category);

    @Query("""
        SELECT DISTINCT p FROM Product p
        JOIN ProductStyle ps ON ps.productId = p.id
        JOIN Style s ON s.id = ps.styleId
        WHERE p.active = true
          AND s.code = :styleCode
          AND (:category IS NULL OR p.category = :category)
        ORDER BY p.id
    """)
    List<Product> findActiveByStyle(@Param("styleCode") String styleCode,
                                    @Param("category") String category);
}
