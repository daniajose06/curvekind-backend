package com.curvekind.backend.repository;

import com.curvekind.backend.entity.ProductBodyShape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductBodyShapeRepository extends JpaRepository<ProductBodyShape, ProductBodyShape.ProductBodyShapeId> {
    List<ProductBodyShape> findByProductId(Long productId);
}
