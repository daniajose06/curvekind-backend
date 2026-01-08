package com.curvekind.backend.repository;

import com.curvekind.backend.entity.BodyShape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BodyShapeRepository extends JpaRepository<BodyShape, Long> {

    Optional<BodyShape> findByCode(String code);
}
