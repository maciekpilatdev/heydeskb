package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.logging.Level;
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
        try {
            return new HttpResponseWrapper(Constans.OK, Constans.GET_BUILGINGS_SUCCESS_MESSAGE, buildingRepository.findByCompany(userRepository.findByUserName(userService.getLogged().getUserName()).get().getCompanyDb().getId()));
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DropdownService: getBuildings: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getFloors(Long buildingId) {
        try {
            if (buildingId != 0) {
                return new HttpResponseWrapper(Constans.OK, Constans.GET_FLOORS_SUCCESS_MESSAGE, floorRepository.findAllByCompanyAndBuilding(userService.getLogged().getCompanyDb().getId(), buildingId));
            } else {
                return new HttpResponseWrapper(Constans.OK, Constans.GET_FLOORS_SUCCESS_MESSAGE, floorRepository.findAll());
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DropdownService: getFloors: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getRooms(Long floorId, Long buildingId) {
        try {
            if (floorId != 0) {
                return new HttpResponseWrapper(Constans.OK, Constans.GET_ROOMS_SUCCESS_MESSAGE, roomRepository.findByFloorAndBuilding(floorId, buildingId));
            } else {
                return new HttpResponseWrapper(Constans.OK, Constans.GET_ROOMS_SUCCESS_MESSAGE, roomRepository.findAll());
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DropdownService: getRooms: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getDesksByRoom(Long roomId, Long floorId, Long buildingId) {
        try {
            if (floorId != 0 && roomId != 0) {
                return new HttpResponseWrapper(Constans.OK, Constans.GET_DESKS_BY_ROOM_SUCCESS_MESSAGE, deskDbRepository.findDesksByBuildingAndFloorAndRoom(roomId, floorId, buildingId));
            } else {
                return new HttpResponseWrapper(Constans.OK, Constans.GET_DESKS_BY_ROOM_SUCCESS_MESSAGE, deskDbRepository.findAll());
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DropdownService: getDesksByRoom: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }
}
