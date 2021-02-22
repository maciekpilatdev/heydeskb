package pl.org.conceptweb.heydeskb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.converter.BuildingConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Building;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.constans.Constans;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    BuildingDbRepository buildingDbRepository;
    @Autowired
    BuildingConverter buildingConverter;

    @PostMapping
    public HttpResponseWrapper addBuilding(@RequestBody Building building) {
        HttpResponseWrapper httpResponseWrapper;
        try {
        buildingDbRepository.save(buildingConverter.buildingToBuildingDb(building));
        httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_BUILDING_SUCCESS_MESSAGE, Arrays.asList(buildingDbRepository.save(buildingConverter.buildingToBuildingDb(building))));    
        } catch (Exception e) {
        httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
