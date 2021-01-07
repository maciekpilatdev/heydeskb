package pl.org.conceptweb.heydeskb.converter;

import ch.qos.logback.classic.Level;
import java.util.ArrayList;
import java.util.List;
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
                deskDb.getDescription(),
                deskDb.getActive(),
                deskDb.getDeleted(),
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
                desk.getDescription(),
                desk.getActive(),
                desk.getDeleted(),
                deskReservationDbsList
        );
    }
}
