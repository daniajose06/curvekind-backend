package com.curvekind.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "product_sizes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "size_code"}))
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "size_code", nullable = false, length = 10)
    private String sizeCode; // S, M, L, XL

    @Column(nullable = false)
    private Boolean active = true;
}
