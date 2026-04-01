package com.drivewealth.configflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "audit_entry")
public class AuditEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_key")
    private String key;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "rollout_percent")
    private int rolloutPercent;

    @Column(name = "config_value", length = 2048)
    private String value;

    @Column(name = "created_at")
    private Instant at;

    @Column(name = "version")
    private long version;

    public AuditEntry() { }

    public AuditEntry(String key, String value, int rolloutPercent, String updatedBy, long version) {
        this.key = key;
        this.value = value;
        this.rolloutPercent = rolloutPercent;
        this.updatedBy = updatedBy;
        this.at = Instant.now();
        this.version = version;
    }

    public Long getId() { return id; }
    public String getKey() { return key; }
    public String getUpdatedBy() { return updatedBy; }
    public int getRolloutPercent() { return rolloutPercent; }
    public String getValue() { return value; }
    public Instant getAt() { return at; }
    public long getVersion() { return version; }
}
