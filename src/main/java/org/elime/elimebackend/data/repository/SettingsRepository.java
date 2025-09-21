package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, String> {
}
