package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.DropdownService;

@RestController
@RequestMapping("/dropdown")
@CrossOrigin(origins = "*")
@Log
public class DropdownController {

    @Autowired
    DropdownService dropdownService;
    
    @GetMapping("/building")
    public HttpResponseWrapper getBuildings(Principal principal) {
        return dropdownService.getBuildings();
    }
    
    @GetMapping("/floor")
    public HttpResponseWrapper getFloors(@RequestParam Long buildingId) {
        return dropdownService.getFloors(buildingId);
    }
    
    @GetMapping("/room")
    public HttpResponseWrapper getRooms(@RequestParam Long floorId, Long buildingId) {
        return dropdownService.getRooms(floorId, buildingId);
    }
    
    @GetMapping("/desk")
    public HttpResponseWrapper getDesksByRoom(@RequestParam Long roomId, @RequestParam Long floorId, Long buildingId) {        
        return dropdownService.getDesksByRoom(roomId, floorId, buildingId);
    }
}
