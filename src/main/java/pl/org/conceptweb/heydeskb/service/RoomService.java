package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.RoomConverter;
import pl.org.conceptweb.heydeskb.inputTest.InputTester;
import pl.org.conceptweb.heydeskb.inputTest.TextInputStrategy;
import pl.org.conceptweb.heydeskb.model.FloorDb;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
import pl.org.conceptweb.heydeskb.model.Room;
import pl.org.conceptweb.heydeskb.model.RoomDb;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.security.SecurityAuthoritiesCheck;

@Log
@Service
public class RoomService {

    @Autowired
    RoomConverter roomConverter;
    @Autowired
    RoomDbRepository roomDbRepository;
    @Autowired
    FloorDbRepository floorDbRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityAuthoritiesCheck securityAuthoritiesCheck;
    @Autowired
    UserService userService;
    @Autowired
    InputTester inputTester;
    @Autowired
    TextInputStrategy textInputStrategy;

    public HttpResponseWrapper add(Room room) {
        try {
            MethodResponse roomName = inputTester.runTest(textInputStrategy, room.getName());
            if (roomName.getStatus().equals(Constans.ERROR)) {
                return new HttpResponseWrapper(Constans.ERROR, roomName.getMessage(), new ArrayList());
            } else if (!isNameUnique(room)) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.NAME_NOT_UNIQUE_ERROR_MESSAGE, new ArrayList());
            } else if (!securityAuthoritiesCheck.hasAuthority(userService.getLogged().getUserName(), Constans.AUTHORITY_ADMIN)) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            } else {
                return new HttpResponseWrapper(Constans.OK, Constans.ADD_ROOM_SUCCESS_MESSAGE, Arrays.asList(roomConverter.roomDbToRoom(roomDbRepository.save(roomConverter.roomToRoomDb(room)))));
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: RoomService: addRoom: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getListByCompany() {
        try {
            return new HttpResponseWrapper(Constans.OK, Constans.GET_FLOOR_LIST_BY_COMPANY_SUCCESS_MESSAGE, roomConverter.roomsDbToRooms(roomDbRepository.findByCompanyId(userRepository.findByUserName(userService.getLogged().getUserName()).get().getCompanyDb().getId())));
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: RoomService: getRoomListByCompany: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper delete(Long roomId) {
        RoomDb roomDb = roomDbRepository.getOne(roomId);
        try {
            if (securityAuthoritiesCheck.hasAuthority(userService.getLogged().getUserName(), Constans.AUTHORITY_ADMIN)) {
                roomDb.setIsDeleted(true);
                roomDbRepository.save(roomDb);
                return new HttpResponseWrapper(Constans.OK, Constans.DELETE_FLOOR_SUCCESS_MESSAGE, new ArrayList());
            } else {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: RoomService: deleteRoom: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public Boolean isNameUnique(Room room) {
        FloorDb floorDb = floorDbRepository.getOne(room.getFloor());
        return roomDbRepository.findAllByCompanyAndBuildingAndFloorAndName(
                floorDb.getBuilding().getCompanyDb().getId(),
                floorDb.getBuilding().getId(),
                floorDb.getId(),
                room.getName()
        ).isEmpty();
    }
}
