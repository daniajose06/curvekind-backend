package com.curvekind.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "product_body_shapes")
@IdClass(ProductBodyShape.ProductBodyShapeId.class)
public class ProductBodyShape {

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Id
    @Column(name = "body_shape_id", nullable = false)
    private Long bodyShapeId;

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class ProductBodyShapeId implements Serializable {
        private Long productId;
        private Long bodyShapeId;
    }
}
