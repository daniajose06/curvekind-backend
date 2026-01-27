package com.curvekind.backend.repository;

import com.curvekind.backend.entity.BodyShapeStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BodyShapeStyleRepository extends JpaRepository<BodyShapeStyle, BodyShapeStyle.BodyShapeStyleId> {

    @Query("""
        SELECT bss
        FROM BodyShapeStyle bss
        JOIN BodyShape bs ON bs.id = bss.bodyShapeId
        WHERE bs.code = :code
        ORDER BY bss.rank ASC, bss.styleId ASC
    """)
    List<BodyShapeStyle> findLinksByBodyShapeCodeOrdered(@Param("code") String bodyShapeCode);
}
