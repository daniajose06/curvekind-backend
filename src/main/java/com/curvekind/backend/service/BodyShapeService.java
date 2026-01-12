package com.curvekind.backend.service;

import com.curvekind.backend.entity.BodyShape;
import com.curvekind.backend.entity.Style;
import com.curvekind.backend.repository.BodyShapeRepository;
import com.curvekind.backend.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyShapeService {

    private final BodyShapeRepository bodyShapeRepository;
    private final StyleRepository styleRepository;

    public List<BodyShape> getAll() {
        return bodyShapeRepository.findAll(Sort.by("id"));
    }

    public BodyShape getByCode(String code) {
        return bodyShapeRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Body shape not found: " + code));
    }

    public List<Style> getRecommendedStyles(String bodyShapeCode) {
        // Ensure body shape exists (cleaner error handling)
        getByCode(bodyShapeCode);
        return styleRepository.findStylesForBodyShape(bodyShapeCode.toUpperCase());
    }
}
