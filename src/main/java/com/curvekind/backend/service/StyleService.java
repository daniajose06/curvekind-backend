package com.curvekind.backend.service;

import com.curvekind.backend.entity.Style;
import com.curvekind.backend.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StyleService {

    private final StyleRepository styleRepository;

    public List<Style> getAll() {
        return styleRepository.findAll(Sort.by("id"));
    }

    public Style getByCode(String code) {
        return styleRepository.findByCode(code.toUpperCase()).orElseThrow(() -> new IllegalArgumentException("Style not found for code:" + code));
    }
}
