package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.Dough;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoughRepository extends JpaRepository<Dough, String> {
}
