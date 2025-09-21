package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.Cream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreamRepository extends JpaRepository<Cream, String> {
}
