package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
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
import pl.org.conceptweb.heydeskb.datacombinator.FloorCombinatorService;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Floor;
import pl.org.conceptweb.heydeskb.service.FloorService;

@Log
@RestController
@RequestMapping("/floor")
@CrossOrigin(origins = "https://heydeskb.herokuapp.com/", maxAge = 3600)
public class FloorController {

    @Autowired
    FloorService floorService;
    @Autowired
    FloorCombinatorService floorDataCombinator;

    @PostMapping()
    public HttpResponseWrapper addFloor(@RequestBody Floor floor) {
        return floorService.add(floor);
    }

    @GetMapping("/company")
    public HttpResponseWrapper getFloorListByCompany() {
        return floorService.getListByCompany();
    }

    @DeleteMapping()
    public HttpResponseWrapper deleteFloor(@RequestParam Long floorId) {
        return floorService.delete(floorId);
    }

    @GetMapping("/company/combinator")
    public HttpResponseWrapper getCombinatedListByCompany() {
        return floorDataCombinator.getListDataByCompany();
    }
}
