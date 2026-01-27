package com.curvekind.backend.service;

import com.curvekind.backend.dto.ProductCreateRequest;
import com.curvekind.backend.dto.ProductImageResponse;
import com.curvekind.backend.dto.ProductResponse;
import com.curvekind.backend.entity.*;
import com.curvekind.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductStyleRepository productStyleRepository;
    private final ProductBodyShapeRepository productBodyShapeRepository;
    private final StyleRepository styleRepository;
    private final BodyShapeRepository bodyShapeRepository;
    private final ProductImageRepository productImageRepository;

    public List<ProductResponse> list(String bodyShape, String style, String category) {
        List<Product> products;

        if (bodyShape != null && !bodyShape.isBlank()) {
            products = productRepository.findActiveByBodyShape(bodyShape.toUpperCase(), category);
        } else if (style != null && !style.isBlank()) {
            products = productRepository.findActiveByStyle(style.toUpperCase(), category);
        } else {
            products = productRepository.findActiveByCategory(category);
        }

        return products.stream().map(this::toResponse).toList();
    }

    public ProductResponse get(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        return toResponse(p);
    }

    @Transactional
    public ProductResponse create(ProductCreateRequest req) {
        Product saved = productRepository.save(Product.builder()
                .name(req.name())
                .description(req.description())
                .category(req.category())
                .priceCents(req.priceCents())
                .active(true)
                .createdAt(Instant.now())
                .build());

        Long productId = saved.getId();

        if (req.images() != null) {
            for (var img : req.images()) {
                productImageRepository.save(ProductImage.builder()
                        .productId(productId)
                        .imageUrl(img.imageUrl())
                        .altText(img.altText())
                        .sortOrder(img.sortOrder())
                        .primary(img.primary())
                        .createdAt(Instant.now())
                        .build());
            }
        }


        // sizes
        for (String size : req.sizes()) {
            productSizeRepository.save(ProductSize.builder()
                    .productId(productId)
                    .sizeCode(size.toUpperCase())
                    .active(true)
                    .build());
        }

        // style mappings
        for (String styleCode : req.styleCodes()) {
            Style style = styleRepository.findByCode(styleCode.toUpperCase())
                    .orElseThrow(() -> new IllegalArgumentException("Unknown style: " + styleCode));

            productStyleRepository.save(ProductStyle.builder()
                    .productId(productId)
                    .styleId(style.getId())
                    .build());
        }

        // body shape mappings
        for (String shapeCode : req.bodyShapeCodes()) {
            BodyShape shape = bodyShapeRepository.findByCode(shapeCode.toUpperCase())
                    .orElseThrow(() -> new IllegalArgumentException("Unknown body shape: " + shapeCode));

            productBodyShapeRepository.save(ProductBodyShape.builder()
                    .productId(productId)
                    .bodyShapeId(shape.getId())
                    .build());
        }

        return toResponse(saved);
    }

    private ProductResponse toResponse(Product p) {
        List<String> sizes = productSizeRepository.findByProductIdOrderByIdAsc(p.getId())
                .stream().map(ProductSize::getSizeCode).toList();

        List<String> styleCodes = productStyleRepository.findByProductId(p.getId()).stream()
                .map(ps -> styleRepository.findById(ps.getStyleId()).orElseThrow().getCode())
                .toList();

        List<String> bodyShapeCodes = productBodyShapeRepository.findByProductId(p.getId()).stream()
                .map(pbs -> bodyShapeRepository.findById(pbs.getBodyShapeId()).orElseThrow().getCode())
                .toList();

        List<ProductImageResponse> images = productImageRepository
                .findByProductIdOrderByIsPrimaryDescSortOrderAscIdAsc(p.getId())
                .stream()
                .map(img -> new ProductImageResponse(
                        img.getId(),
                        img.getImageUrl(),
                        img.getAltText(),
                        img.getSortOrder(),
                        img.getPrimary()
                ))
                .toList();


        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getCategory(),
                p.getPriceCents(),
                p.getActive(),
                p.getCreatedAt(),
                sizes,
                styleCodes,
                bodyShapeCodes,
                images
        );
    }
}
