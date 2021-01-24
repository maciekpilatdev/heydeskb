package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;
import pl.org.conceptweb.heydeskb.model.DeskReservation;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@Service
@Log
public class DeskReservationConverter {

    @Autowired
    DeskDbRepository deskDbRepository;
    @Autowired
    UserRepository userRepository;

    public DeskReservationDb deskReservationToDeskReservationDb(DeskReservation deskReservation) {
        return new DeskReservationDb(
                deskReservation.getId(),
                deskDbRepository.getOne(deskReservation.getDeskId()),
                deskReservation.getStartReservation(),
                deskReservation.getEndReservation(),
                userRepository.getOne(deskReservation.getUserId())
        );
    }

    public DeskReservation deskReservationDbToDeskReservation(DeskReservationDb deskReservationDb) {
        return new DeskReservation(
                deskReservationDb.getId(),
                deskReservationDb.getDeskId().getId(),
                deskReservationDb.getStartReservation(),
                deskReservationDb.getEndReservation(),
                deskReservationDb.getUser().getId());
    }

    public List<DeskReservationDb> deskReservationsToDeskReservationsDb(List<DeskReservation> deskReservations) {
        List<DeskReservationDb> deskReservationsDb = new ArrayList();
        try {
            deskReservations.forEach(deskReservation -> {
                deskReservationsDb.add(deskReservationToDeskReservationDb(deskReservation));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "DeskReservationConverter: deskReservationsToDeskReservationsDb: ERROR: " + e);
        }
        return deskReservationsDb;
    }

    public List<DeskReservation> deskReservationsDbToDeskReservations(List<DeskReservationDb> deskReservationsDb) {
        List<DeskReservation> deskReservations = new ArrayList();
        try {
            deskReservationsDb.forEach(deskReservationDb -> {
                deskReservations.add(deskReservationDbToDeskReservation(deskReservationDb));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "DeskReservationConverter: deskReservationsDbToDeskReservations: ERROR: " + e);
        }
        return null;
    }
}
