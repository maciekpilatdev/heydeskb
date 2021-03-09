package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@Log
@Service
public class DropdownService {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BuildingDbRepository buildingRepository;
    @Autowired
    FloorDbRepository floorRepository;
    @Autowired
    RoomDbRepository roomRepository;
    @Autowired
    DeskDbRepository deskDbRepository;

    public HttpResponseWrapper getBuildings() {
        HttpResponseWrapper hrw;
        try {
            hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_BUILGINGS_SUCCESS_MESSAGE, buildingRepository.findByCompany(userRepository.findByUserName(userService.getLogged().getUserName()).get().getCompanyDb().getId()));
        } catch (Exception e) {
            hrw = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return hrw;
    }

    public HttpResponseWrapper getFloors(Long buildingId) {
        HttpResponseWrapper hrw;
        try {
            if (buildingId != 0) {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOORS_SUCCESS_MESSAGE, floorRepository.findAllByCompanyAndBuilding(userService.getLogged().getCompanyDb().getId(), buildingId));
            } else {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOORS_SUCCESS_MESSAGE, floorRepository.findAll());
            }
        } catch (Exception e) {
            hrw = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return hrw;
    }

    public HttpResponseWrapper getRooms(Long floorId, Long buildingId) {
        HttpResponseWrapper hrw;
        try {
            if (floorId != 0) {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_ROOMS_SUCCESS_MESSAGE, roomRepository.findByFloorAndBuilding(floorId, buildingId));
            } else {
                hrw = new HttpResponseWrapper(Constans.OK, Constans.GET_ROOMS_SUCCESS_MESSAGE, roomRepository.findAll());
            }
        } catch (Exception e) {
            hrw = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return hrw;
    }

    public HttpResponseWrapper getDesksByRoom(Long roomId, Long floorId, Long buildingId) {
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
