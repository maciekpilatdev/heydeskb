package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Building;
import pl.org.conceptweb.heydeskb.service.BuildingService;

@Log
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @PostMapping
    public HttpResponseWrapper addBuilding(@RequestBody Building building, Principal principal) {
        return buildingService.addBuilding(building, principal.getName());
    }

    @GetMapping
    public HttpResponseWrapper getBuildingListByCompany(Principal principal) {
        return buildingService.getBuildingListByCompany(principal.getName());
    }

    @DeleteMapping()
    public HttpResponseWrapper deleteBuilding(@RequestParam Long buildingId, Principal principal) {
        return buildingService.deleteBuilding(buildingId, principal.getName());
    }
}
