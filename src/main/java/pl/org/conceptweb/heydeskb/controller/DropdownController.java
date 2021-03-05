package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

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
    @Autowired
    UserRepository userRepository;

    @GetMapping("/building")
    public HttpResponseWrapper getBuildings(Principal principal) {
        HttpResponseWrapper hrw;
        try {
            hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_BUILGINGS_SUCCESS_MESSAGE, buildingRepository.getAllByCompany(userRepository.findByUserName(principal.getName()).get().getCompanyDb().getId()));
        } catch (Exception e) {
            hrw = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return hrw;
    }

    @GetMapping("/floor")
    public HttpResponseWrapper getFloors(@RequestParam Long buildingId) {
        HttpResponseWrapper hrw;

        try {
            if (buildingId != 0) {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOORS_SUCCESS_MESSAGE , floorRepository.findByBuildingId(buildingId));
            } else {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOORS_SUCCESS_MESSAGE, floorRepository.findAll());
            }
        } catch (Exception e) {
            hrw = new HttpResponseWrapper(Constans.ERROR, e.toString() ,  new ArrayList());
        }
        log.log(Level.WARNING, "getFloors: " + hrw.getResult());
        return hrw;
    }

    @GetMapping("/room")
    public HttpResponseWrapper getRooms(@RequestParam Long floorId, Long buildingId) {
        HttpResponseWrapper hrw;
        try {
            if (floorId != 0) {                
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_ROOMS_SUCCESS_MESSAGE, roomRepository.findByFloorAndBuilding(floorId, buildingId));
            } else {                
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_ROOMS_SUCCESS_MESSAGE, roomRepository.findAll());
            }
        } catch (Exception e) {
            hrw = new HttpResponseWrapper(Constans.ERROR, e.toString(),  new ArrayList());
        }
        return hrw;
    }

    @GetMapping("/desk")
    public HttpResponseWrapper getDesksByRoom(@RequestParam Long roomId, @RequestParam Long floorId, Long buildingId){        
        HttpResponseWrapper hrw;
        try {
            if (floorId != 0 && roomId != 0) {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_DESKS_BY_ROOM_SUCCESS_MESSAGE, deskDbRepository.findDesksByBuildingAndFloorAndRoom(roomId, floorId, buildingId));
            } else {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_DESKS_BY_ROOM_SUCCESS_MESSAGE, deskDbRepository.findAll());
            }
        } catch (Exception e) {
            hrw = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return hrw;
    }
}