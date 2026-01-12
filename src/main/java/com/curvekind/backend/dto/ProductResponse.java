package com.curvekind.backend.dto;

import java.time.Instant;
import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String category,
        Integer priceCents,
        Boolean active,
        Instant createdAt,
        List<String> sizes,
        List<String> styleCodes,
        List<String> bodyShapeCodes
) {}
