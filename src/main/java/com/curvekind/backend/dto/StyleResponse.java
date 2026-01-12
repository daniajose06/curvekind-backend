package com.curvekind.backend.dto;

public record StyleResponse(
        Long id,
        String code,
        String displayName,
        String description
) {}
