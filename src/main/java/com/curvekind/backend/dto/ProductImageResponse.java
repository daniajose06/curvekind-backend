package com.curvekind.backend.dto;

public record ProductImageResponse(
        Long id,
        String imageUrl,
        String altText,
        Integer sortOrder,
        Boolean primary
) {}