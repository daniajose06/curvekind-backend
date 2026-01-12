package com.curvekind.backend.controller;

import com.curvekind.backend.dto.ProductCreateRequest;
import com.curvekind.backend.dto.ProductResponse;
import com.curvekind.backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> list(
            @RequestParam(required = false) String bodyShape,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String category
    ) {
        return productService.list(bodyShape, style, category);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return productService.get(id);
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductCreateRequest req) {
        return productService.create(req);
    }
}
