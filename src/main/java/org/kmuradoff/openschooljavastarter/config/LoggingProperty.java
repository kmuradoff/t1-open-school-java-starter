package org.kmuradoff.openschooljavastarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "t1.logging")
public class LoggingProperty {

    /**
     * Enable or disable HTTP logging.
     */
    private boolean enabled = true;

    /**
     * Log level: INFO, DEBUG, WARN, ERROR.
     */
    private String level = "INFO";

    // Getters and setters
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
}