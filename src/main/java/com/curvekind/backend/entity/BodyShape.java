package com.curvekind.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "body_shapes")
public class BodyShape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String code;

    @Column(name =  "display_name", nullable = false, length = 80)
    private String displayName;

    @Column(columnDefinition = "TEXT")
    private String description;

}
