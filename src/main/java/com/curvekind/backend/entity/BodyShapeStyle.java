package com.curvekind.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "body_shape_styles")
@IdClass(BodyShapeStyle.BodyShapeStyleId.class)
public class BodyShapeStyle {

    @Id
    @Column(name = "body_shape_id", nullable = false)
    private Long bodyShapeId;

    @Id
    @Column(name = "style_id", nullable = false)
    private Long styleId;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BodyShapeStyleId implements Serializable {
        private Long bodyShapeId;
        private Long styleId;
    }
}
