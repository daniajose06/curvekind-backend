package com.curvekind.backend.controller;

import com.curvekind.backend.dto.BodyShapeResponse;
import com.curvekind.backend.entity.BodyShape;
import com.curvekind.backend.repository.BodyShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/body-shapes")
@RequiredArgsConstructor
public class BodyShapeController {

    private final BodyShapeRepository bodyShapeRepository;

    @GetMapping
    public List<BodyShapeResponse> list() {
        return bodyShapeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{code}")
    public BodyShapeResponse getByCode(@PathVariable String code) {
        BodyShape bodyShape = bodyShapeRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Body shape not found: " + code));
        return toResponse(bodyShape);
    }

    private BodyShapeResponse toResponse(BodyShape bodyShape) {
        return new BodyShapeResponse(
                bodyShape.getId(),
                bodyShape.getCode(),
                bodyShape.getDisplayName(),
                bodyShape.getDescription()
        );
    }
}
