package hello.service;// Copyright (c) 2015 Travelex Ltd


import hello.model.FeatureToggle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
public class ToggleServiceImpl implements ToggleService {
    private static final HashMap<String, HashMap<String, FeatureToggle>> services = new HashMap<>();

    @Override
    public void crupdateServiceToggles(String serviceName, FeatureToggle toggle) {
        HashMap toggles = services.computeIfAbsent(serviceName, k -> new HashMap<>());
        toggles.put(toggle.getName(), toggle);
    }

    @Override
    public FeatureToggle getToggle(String serviceName, String toggleName) {
        HashMap<String, FeatureToggle> toggles = services.get(serviceName);
        return toggles != null ? toggles.get(toggleName) : null;
    }

    @Override
    public Set<FeatureToggle> getToggles(String serviceName) {
        HashMap<String, FeatureToggle> toggles = services.get(serviceName);
        return toggles != null ? new HashSet<>(toggles.values()) : new HashSet<>();
    }

    @Override
    public void updateToggle(String serviceName, String toggleName, Boolean value) {
        HashMap<String, FeatureToggle> toggles = services.get(serviceName);
        FeatureToggle toggleToUpdate = toggles.get(toggleName);
        toggleToUpdate.setValue(value);
    }
}
