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
        return new Room(roomDb.getId(), roomDb.getName(), roomDb.getFloor().getId(), deskConverter.deskDbToIdList(roomDb.getDesks()), roomDb.getIsDeleted());
    }

    public RoomDb roomToRoomDb(Room room) {
        return new RoomDb(room.getId(), room.getName(), floorDbRepository.getOne(room.getFloor()), deskConverter.idListToDeskDb(room.getDesks()), room.getIsDeleted());
    }

    public List<Room> roomsDbToRooms(List<RoomDb> roomsDb) {

        List<Room> rooms = new ArrayList();
        try {
            roomsDb.forEach(roomDb -> {
                rooms.add(roomDbToRoom(roomDb));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "ERROR: RoomConverter: roomsDbToRooms: " + e);
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
            log.log(java.util.logging.Level.WARNING, "ERROR: FloorConverter: floorsToFloorDb: " + e);
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
            log.log(Level.WARNING, "ERROR: RoomConverter: roomsDbToIdList: " + e);
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
            log.log(Level.WARNING, "ERROR: RoomConverter: idListToRoomsDb: : " + e);
        }
        return roomsDb;
    }
}
