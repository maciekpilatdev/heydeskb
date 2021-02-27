package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.Floor;
import pl.org.conceptweb.heydeskb.model.FloorDb;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;

@Log
@Component
public class FloorConverter {
    @Autowired
    FloorDbRepository floorDbRepository;
    @Autowired
    BuildingDbRepository buildingDbRepository;
    @Autowired
    RoomDbRepository roomDbRepository;
    @Autowired
    RoomConverter roomConverter;

   public Floor floorDbToFloor(FloorDb floorDb) {
        return new Floor(floorDb.getId(), floorDb.getName(), floorDb.getBuilding().getId(), roomConverter.roomsDbToIdList(floorDb.getRooms()));
    }

    public FloorDb floorToFloorDb(Floor floor) {
        return new FloorDb(floor.getId(), floor.getName(), buildingDbRepository.getOne(floor.getBuilding()), roomConverter.idListToRoomsDb(floor.getRooms()));
    }

    public List<Floor> floorsDbToFloors(List<FloorDb> floorsDb) {

        List<Floor> floors = new ArrayList();
        try {
            floorsDb.forEach(floorDb -> {
                floors.add(floorDbToFloor(floorDb));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "FloorConverter: floorsDbToFloors: ERROR: " + e);
        }
        return floors;
    }

    public List<FloorDb> floorsToFloorDb(List<Floor> floors) {

        List<FloorDb> floorsDb = new ArrayList();
        try {
            floors.forEach(floor -> {
                floorsDb.add(floorToFloorDb(floor));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "FloorConverter: floorsToFloorDb: ERROR: " + e);
        }
        return floorsDb;
    }   
    
         public List<Long> floorsDbToIdList(List<FloorDb> floorsDb) {
        List<Long> floors = new ArrayList();
        try {
            floorsDb.forEach(floorDb -> {
                floors.add(floorDb.getId());
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "FloorConverter: floorsDbToIdList: ERROR: " + e);
        }
        return floors;
    }
     
         public List<FloorDb> idListToFloorsDb(List<Long> IdList) {
        List<FloorDb> floorsDb = new ArrayList();
        try {
            IdList.forEach(floor -> {
                floorsDb.add(floorDbRepository.getOne(floor));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "FloorConverter: idListToFloorsDb: ERROR: " + e);
        }
        return floorsDb;
    }
}
