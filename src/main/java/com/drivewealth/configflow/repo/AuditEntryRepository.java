package com.drivewealth.configflow.repo;

import com.drivewealth.configflow.model.AuditEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditEntryRepository extends JpaRepository<AuditEntry, Long> {
}
