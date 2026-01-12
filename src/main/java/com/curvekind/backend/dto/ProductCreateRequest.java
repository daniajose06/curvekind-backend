package com.curvekind.backend.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record ProductCreateRequest(
        @NotBlank String name,
        String description,
        String category,

        @NotNull @Min(0) Integer priceCents,

        @NotNull List<@NotBlank String> sizes,          // ["S","M","L","XL"]
        @NotNull List<@NotBlank String> styleCodes,     // ["A_LINE_DRESS"]
        @NotNull List<@NotBlank String> bodyShapeCodes  // ["PEAR","APPLE"]
) {}
