package com.curvekind.backend.repository;

import com.curvekind.backend.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, Long> {

    Optional<Style> findByCode(String code);

    @Query("""
        SELECT s
        FROM Style s
        JOIN BodyShapeStyle bss ON bss.styleId = s.id
        JOIN BodyShape bs ON bs.id = bss.bodyShapeId
        WHERE bs.code = :code
        ORDER BY s.id
    """)
    List<Style> findStylesForBodyShape(@Param("code") String bodyShapeCode);
}
