package com.curvekind.backend.service;

import com.curvekind.backend.entity.BodyShape;
import com.curvekind.backend.entity.Style;
import com.curvekind.backend.repository.BodyShapeRepository;
import com.curvekind.backend.repository.BodyShapeStyleRepository;
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
    private final BodyShapeStyleRepository bodyShapeStyleRepository;

    public List<BodyShape> getAll() {
        return bodyShapeRepository.findAll(Sort.by("id"));
    }

    public BodyShape getByCode(String code) {
        return bodyShapeRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new com.curvekind.backend.exception.NotFoundException("Body shape not found: " + code));

    }


    public List<Style> getRecommendedStyles(String bodyShapeCode) {
        // Ensure body shape exists (for a good 404 later)
        getByCode(bodyShapeCode);

        return bodyShapeStyleRepository
                .findLinksByBodyShapeCodeOrdered(bodyShapeCode.toUpperCase())
                .stream()
                .map(link -> styleRepository.findById(link.getStyleId())
                        .orElseThrow(() -> new IllegalStateException("Style missing for id: " + link.getStyleId())))
                .toList();
    }

}
