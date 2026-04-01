package com.drivewealth.configflow.repo;

import com.drivewealth.configflow.model.ConfigEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigEntryRepository extends JpaRepository<ConfigEntry, String> {
}
