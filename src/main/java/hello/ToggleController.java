package hello;

import hello.exceptions.NotFoundException;
import hello.model.FeatureToggle;
import hello.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RestController
@CrossOrigin
public class ToggleController {

    @Autowired
    ToggleService toggleService;

    @RequestMapping(value="/services/{service}/toggles", consumes="application/json", method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createToggle(@RequestBody FeatureToggle featureToggle, @PathVariable String service) {
        toggleService.crupdateServiceToggles(service, featureToggle);
    }

    @RequestMapping(value="/services/{service}/toggles/{toggle}", produces="application/json", method= RequestMethod.GET)
    public FeatureToggle getToggle(@PathVariable String service, @PathVariable String toggle) {
        FeatureToggle existingToggle = toggleService.getToggle(service, toggle);
        if (existingToggle == null) {
            throw new NotFoundException();
        }
        return existingToggle;
    }

    @RequestMapping(value="/services/{service}/toggles", produces="application/json", method= RequestMethod.GET)
    public Set<FeatureToggle> getToggles(@PathVariable String service) {
        return toggleService.getToggles(service);
    }

    @RequestMapping(value="/services/{service}/toggles/{toggle}/on", produces="application/json", method= RequestMethod.PUT)
    public void turnOnToggle(@PathVariable String service, @PathVariable String toggle) {
        toggleService.updateToggle(service, toggle, true);
    }


    @RequestMapping(value="/services/{service}/toggles/{toggle}/off", produces="application/json", method= RequestMethod.PUT)
    public void turnOffToggle(@PathVariable String service, @PathVariable String toggle) {
        toggleService.updateToggle(service, toggle, false);
    }
}
