package com.curvekind.backend.dto;

public record BodyShapeResponse(
        Long id,
        String code,
        String displayName,
        String description
) {}
