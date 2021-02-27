package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.Room;
import pl.org.conceptweb.heydeskb.model.RoomDb;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;

@Log
@Component
public class RoomConverter {

    @Autowired
    RoomDbRepository roomDbRepository;
    @Autowired
    DeskConverter deskConverter;
    @Autowired
    FloorDbRepository floorDbRepository;

    public Room roomDbToRoom(RoomDb roomDb) {
        return new Room(roomDb.getId(), roomDb.getName(), roomDb.getFloor().getId(), deskConverter.deskDbToIdList(roomDb.getDesks()));
    }

    public RoomDb roomToRoomDb(Room room) {
        return new RoomDb(room.getId(), room.getName(), floorDbRepository.getOne(room.getFloor()), deskConverter.idListToDeskDb(room.getDesks()));
    }

    public List<Long> roomsDbToRooms(List<RoomDb> roomsDb) {

        List<Long> rooms = new ArrayList();
        try {
            roomsDb.forEach(roomDb -> {
                rooms.add(roomDb.getId());
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "RoomConverter: roomsDbToRooms: ERROR: " + e);
        }
        return rooms;
    }

    public List<RoomDb> roomsToRoomDb(List<Room> rooms) {

        List<RoomDb> roomsDb = new ArrayList();
        try {
            rooms.forEach(room -> {
                roomsDb.add(roomToRoomDb(room));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "FloorConverter: floorsToFloorDb: ERROR: " + e);
        }
        return roomsDb;
    }

    public List<Long> roomsDbToIdList(List<RoomDb> roomsDb) {
        List<Long> rooms = new ArrayList();
        try {
            roomsDb.forEach(roomDb -> {
                rooms.add(roomDb.getId());
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "RoomConverter: roomsDbToIdList: ERROR: " + e);
        }
        return rooms;
    }

    public List<RoomDb> idListToRoomsDb(List<Long> IdList) {
        List<RoomDb> roomsDb = new ArrayList();
        try {
            IdList.forEach(roomDb -> {
                roomsDb.add(roomDbRepository.getOne(roomDb));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "RoomConverter: idListToRoomsDb: ERROR: " + e);
        }
        return roomsDb;
    }
}
