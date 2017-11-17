package hello.service;// Copyright (c) 2015 Travelex Ltd


import hello.model.FeatureToggle;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.stereotype.Component;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ToggleServiceImpl implements ToggleService {
    private HashMap<String, HashMap<String, FeatureToggle>> services;

    @PostConstruct
    public void setUo() {
        try {
            FileInputStream in = new FileInputStream("db");
            ObjectInputStream oin = new ObjectInputStream(in);
            services = (HashMap<String, HashMap<String, FeatureToggle>>) oin.readObject();
        } catch (Exception e) {
            services = new HashMap<>();
        }
    }

    @PreDestroy
    public void tearDown() {
        try {
            FileOutputStream out = new FileOutputStream("db");
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(services);
            oout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        toggleToUpdate.setEnabled(value);
    }
}
