package pl.org.conceptweb.heydeskb.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;
import pl.org.conceptweb.heydeskb.model.DeskReservation;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@Service
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
}
