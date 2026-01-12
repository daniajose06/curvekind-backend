package com.curvekind.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "product_styles")
@IdClass(ProductStyle.ProductStyleId.class)
public class ProductStyle {

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Id
    @Column(name = "style_id", nullable = false)
    private Long styleId;

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class ProductStyleId implements Serializable {
        private Long productId;
        private Long styleId;
    }
}
