package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Floor;
import pl.org.conceptweb.heydeskb.utility.FloorService;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    FloorService floorService;

    @PostMapping()
    public HttpResponseWrapper addFloor(@RequestBody Floor floor, Principal principal) {
        return floorService.addFloor(floor, principal.getName());
    }
}
