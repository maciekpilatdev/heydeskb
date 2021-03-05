package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.RoomConverter;
import pl.org.conceptweb.heydeskb.model.FloorDb;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Room;
import pl.org.conceptweb.heydeskb.model.RoomDb;
import pl.org.conceptweb.heydeskb.model.User;
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

    public HttpResponseWrapper addRoom(Room room) {
        HttpResponseWrapper httpResponseWrapper;
        Boolean hasAuthority = securityAuthoritiesCheck.hasAuthority(userService.getLoggedUser().getUserName(), Constans.AUTHORITY_ADMIN);
        Boolean isNameUnique = isNameUnique(room);
        try {
            if (hasAuthority && isNameUnique) {
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_ROOM_SUCCESS_MESSAGE, Arrays.asList(roomConverter.roomDbToRoom(roomDbRepository.save(roomConverter.roomToRoomDb(room)))));
            } else if (hasAuthority) {
                httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.NAME_NOT_UNIQUE_ERROR_MESSAGE, new ArrayList());
            } else {
                httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper getRoomListByCompany(String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOOR_LIST_BY_COMPANY_SUCCESS_MESSAGE, roomConverter.roomsDbToRooms(roomDbRepository.findByCompanyId(userRepository.findByUserName(loggedUserName).get().getCompanyDb().getId())));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper deleteRoom(Long roomId, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        RoomDb roomDb = roomDbRepository.getOne(roomId);
        try {
            if (securityAuthoritiesCheck.hasAuthority(loggedUserName, "ADMIN")) {
                roomDb.setIsDeleted(true);
                roomDbRepository.save(roomDb);
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_FLOOR_SUCCESS_MESSAGE, new ArrayList());
            } else {
                httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
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
