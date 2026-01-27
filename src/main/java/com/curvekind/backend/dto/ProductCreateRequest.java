package com.curvekind.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record ProductCreateRequest(
        @NotBlank String name,
        String description,
        String category,

        @NotNull @Min(0) Integer priceCents,

        @NotNull List<@NotBlank String> sizes,          // ["S","M","L","XL"]
        @NotNull List<@NotBlank String> styleCodes,     // ["A_LINE_DRESS"]
        @NotNull List<@NotBlank String> bodyShapeCodes,  // ["PEAR","APPLE"]

        List<@Valid ProductImageCreate> images
) {
    public record ProductImageCreate(
            @NotBlank String imageUrl,
            String altText,
            @NotNull Integer sortOrder,
            @NotNull Boolean primary
    ) {}
}
