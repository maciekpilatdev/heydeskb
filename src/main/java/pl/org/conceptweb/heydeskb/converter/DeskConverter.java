package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;
import pl.org.conceptweb.heydeskb.model.DeskReservation;

@Log
@Component
public class DeskConverter {

    @Autowired
    RoomDbRepository roomDbRepository;
    @Autowired
    DeskDbRepository deskDbRepository;
    @Autowired
    DeskReservationConverter deskReservationConverter;

    public Desk deskDbToDesk(DeskDb deskDb) {

        List<DeskReservation> deskReservationsList = new ArrayList();

        deskDb.getDeskReservations().forEach(deskReservation -> {
            deskReservationsList.add(deskReservationConverter.deskReservationDbToDeskReservation(deskReservation));
        }
        );
        return new Desk(
                deskDb.getId(),
                deskDb.getBuildingId(),
                deskDb.getFloorId(),
                deskDb.getRoomDb().getId(),
                deskDb.getDesksInRoom(),
                deskDb.getNextToWindow(),
                deskDb.getName(),
                deskDb.getActive(),
                deskDb.getIsDeleted(),
                deskReservationsList
        );
    }

    public DeskDb deskToDeskDb(Desk desk) {
        List<DeskReservationDb> deskReservationDbsList = new ArrayList();

        try {
            desk.getDeskReservations().forEach(deskReservation -> {
                deskReservationDbsList.add(deskReservationConverter.deskReservationToDeskReservationDb(deskReservation));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "DeskConverter: deskToDeskDb: ERROR: " + e);
        }

        return new DeskDb(
                desk.getId(),
                desk.getBuildingId(),
                desk.getFloorId(),
                roomDbRepository.getOne(desk.getRoomId()),
                desk.getDesksInRoom(),
                desk.getNextToWindow(),
                desk.getName(),
                desk.getActive(),
                desk.getIsDeleted(),
                deskReservationDbsList
        );
    }

        public List<DeskDb> desksToDesksDb(List<Desk> desks) {
        List<DeskDb> desksDb = new ArrayList();
        try {
            desks.forEach(desk -> {
                desksDb.add(deskToDeskDb(desk));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "DeskReservationConverter: deskReservationsToDeskReservationsDb: ERROR: " + e);
        }
        return desksDb;
    }

    public List<Desk> desksDbToDesks(List<DeskDb> desksDb) {
        List<Desk> desks = new ArrayList();
        try {
            desksDb.forEach(deskDb -> {
                desks.add(deskDbToDesk(deskDb));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "DeskReservationConverter: deskReservationsDbToDeskReservations: ERROR: " + e);
        }
        return desks;
    }
  
    public List<Long> deskDbToIdList(List<DeskDb> desksDb) {
        List<Long> desks = new ArrayList();
        try {
            desksDb.forEach(deskDb -> {
                desks.add(deskDb.getId());
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "DeskConverter: deskDbToIdList: ERROR: " + e);
        }
        return desks;
    }

    public List<DeskDb> idListToDeskDb(List<Long> IdList) {
        List<DeskDb> desksDb = new ArrayList();
        try {
            IdList.forEach(desk -> {
                desksDb.add(deskDbRepository.getOne(desk));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "DeskConverter: idListToDeskDb: ERROR: " + e);
        }
        return desksDb;
    }
}
