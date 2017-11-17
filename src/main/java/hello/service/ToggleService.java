package hello.service;// Copyright (c) 2015 Travelex Ltd


import hello.model.FeatureToggle;

import java.util.Set;

public interface ToggleService {
    void crupdateServiceToggles(String service, FeatureToggle featureToggle);

    FeatureToggle getToggle(String service, String toggle);

    Set<FeatureToggle> getToggles(String service);

    void updateToggle(String service, String toggle, Boolean value);
}
