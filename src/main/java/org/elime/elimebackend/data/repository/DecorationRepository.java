package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.Decoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, String> {
}
