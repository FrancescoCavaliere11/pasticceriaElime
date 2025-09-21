package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, String> {
}
