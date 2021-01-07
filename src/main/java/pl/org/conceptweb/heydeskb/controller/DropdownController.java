package pl.org.conceptweb.heydeskb.controller;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;

@RestController
@RequestMapping("/dropdown")
@CrossOrigin(origins = "*")
@Log
public class DropdownController {

    @Autowired
    BuildingDbRepository buildingRepository;
    @Autowired
    FloorDbRepository floorRepository;
    @Autowired
    RoomDbRepository roomRepository;
    @Autowired
    DeskDbRepository deskDbRepository;

    @GetMapping("/building")
    public HttpResponseWrapper getBuildings() {
        HttpResponseWrapper hrw;
        try {
            hrw = new HttpResponseWrapper("ok", buildingRepository.findAll());
        } catch (Exception e) {
            hrw = new HttpResponseWrapper("error: " + e, buildingRepository.findAll());
        }
        log.log(Level.WARNING, "getBuildings: " + hrw.getResult());
        return hrw;
    }

    @GetMapping("/floor")
    public HttpResponseWrapper getFloors(@RequestParam Long buildingId) {
        HttpResponseWrapper hrw;

        try {
            if (buildingId != 0) {
                hrw = new HttpResponseWrapper("ok", floorRepository.findByBuildingId(buildingId));
            } else {
                hrw = new HttpResponseWrapper("ok", floorRepository.findAll());
            }
        } catch (Exception e) {
            hrw = new HttpResponseWrapper("error: " + e, null);
        }
        return hrw;
    }

    @GetMapping("/room")
    public HttpResponseWrapper getRooms(@RequestParam Long floorId, Long buildingId) {
        
        
        HttpResponseWrapper hrw;
        try {
            if (floorId != 0) {
                log.log(Level.WARNING, "AAA floorId: " + floorId + " / buildingId: " + buildingId);
                hrw = new HttpResponseWrapper("ok", roomRepository.findByFloorAndBuilding(floorId, buildingId));
            } else {
                log.log(Level.WARNING, "BBB floorId: " + floorId + " / buildingId: " + buildingId);
                hrw = new HttpResponseWrapper("ok", roomRepository.findAll());
            }
        } catch (Exception e) {
             log.log(Level.WARNING, "DropdownController / getRooms: " + e);
            hrw = new HttpResponseWrapper("error: " + e, roomRepository.findAll());
        }
        return hrw;
    }

    @GetMapping("/desk")
    public HttpResponseWrapper getDesksByRoom(@RequestParam Long roomId, @RequestParam Long floorId, Long buildingId){
        
        HttpResponseWrapper hrw;
        try {
            if (floorId != 0 && roomId != 0) {
                log.log(Level.WARNING, "desk A floorId: " + floorId + " / buildingId: " + buildingId + " / roomId: " + roomId);
                hrw = new HttpResponseWrapper("ok", deskDbRepository.getDesksByBuildingAndFloorAndRoom(roomId, floorId, buildingId));
            } else {
                log.log(Level.WARNING, "desk b floorId: " + floorId + " / buildingId: " + buildingId + " / roomId: " + roomId);
                hrw = new HttpResponseWrapper("ok", deskDbRepository.findAll());
            }
        } catch (Exception e) {
             log.log(Level.WARNING, "DropdownController / getRooms: " + e);
            hrw = new HttpResponseWrapper("error: " + e, deskDbRepository.findAll());
        }
        return hrw;
    }
}
//http://localhost:8080/dropdown/desk?roomId=1&floorId=1&buildingId=1