package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return buildingService.addBuikding(building, principal.getName());
    }
}
