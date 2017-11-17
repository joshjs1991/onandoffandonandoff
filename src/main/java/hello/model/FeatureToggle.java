// Copyright (c) 2015 Travelex Ltd

package hello.model;

import java.io.Serializable;

public class FeatureToggle implements Serializable {
    private String name;
    private boolean enabled;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
