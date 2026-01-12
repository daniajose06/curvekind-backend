package com.curvekind.backend.controller;

import com.curvekind.backend.dto.StyleResponse;
import com.curvekind.backend.entity.Style;
import com.curvekind.backend.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/styles")
@RequiredArgsConstructor
public class StyleController {

    private final StyleService styleService;

    @GetMapping
    public List<StyleResponse> list() {
        return styleService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{code}")
    public StyleResponse getByCode(@PathVariable String code) {
        return toResponse(styleService.getByCode(code));
    }

    private StyleResponse toResponse(Style style) {
        return new StyleResponse(
                style.getId(),
                style.getCode(),
                style.getDisplayName(),
                style.getDescription()
        );
    }
}
