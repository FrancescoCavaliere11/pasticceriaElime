package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, String> {
    boolean existsByToken(String token);
}
