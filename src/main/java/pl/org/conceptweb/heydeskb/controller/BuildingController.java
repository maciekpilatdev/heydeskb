package pl.org.conceptweb.heydeskb.controller;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.BuildingDb;
import pl.org.conceptweb.heydeskb.service.BuildingService;

@Log
@RestController
@RequestMapping("/building")
@CrossOrigin(origins = "https://heydeskb.herokuapp.com/", maxAge = 3600)
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @PostMapping
    public HttpResponseWrapper addBuilding(@RequestBody BuildingDb buildingDb) {
        try {
            return buildingService.add(buildingDb).get();
        } catch (InterruptedException | ExecutionException ex) {
            log.log(Level.WARNING, "ERROR: BuildingController: addBuilding: ", ex);
            return new HttpResponseWrapper(Constans.ERROR, Constans.TRY_LATER, Collections.singletonList(null));
        }
    }

    @GetMapping
    public HttpResponseWrapper getBuildingListByCompany() {
        return buildingService.getListByCompany();
    }

    @DeleteMapping()
    public HttpResponseWrapper deleteBuilding(@RequestParam Long buildingId) {
        return buildingService.deleteBuilding(buildingId);
    }
}
