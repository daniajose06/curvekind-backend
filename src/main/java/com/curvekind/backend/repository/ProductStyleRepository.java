package com.curvekind.backend.repository;

import com.curvekind.backend.entity.ProductStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductStyleRepository extends JpaRepository<ProductStyle, ProductStyle.ProductStyleId> {
    List<ProductStyle> findByProductId(Long productId);
}
