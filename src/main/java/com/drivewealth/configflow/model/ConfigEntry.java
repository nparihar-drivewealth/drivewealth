package com.drivewealth.configflow.model;

import java.time.Instant;

//This class represents a configuration entry in the system. It contains the key, value, rollout percentage (indicating what percentage of users should receive this config), who last updated it, when it was last updated, and a version number for optimistic locking.
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "config_entry")
public class ConfigEntry {
    @Id
    @Column(name = "config_key")
    private String key;

    @Column(name = "config_value", length = 2048)
    private String value;

    @Column(name = "rollout_percent")
    private int rolloutPercent; // 0-100

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "version")
    private long version;

    public ConfigEntry() { }

    public ConfigEntry(String key, String value, int rolloutPercent, String updatedBy, long version) {
        this.key = key;
        this.value = value;
        this.rolloutPercent = rolloutPercent;
        this.updatedBy = updatedBy;
        this.updatedAt = Instant.now();
        this.version = version;
    }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public int getRolloutPercent() { return rolloutPercent; }
    public void setRolloutPercent(int rolloutPercent) { this.rolloutPercent = rolloutPercent; }
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    public long getVersion() { return version; }
    public void setVersion(long version) { this.version = version; }
}
