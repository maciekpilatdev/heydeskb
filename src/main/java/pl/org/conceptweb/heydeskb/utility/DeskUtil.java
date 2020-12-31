package pl.org.conceptweb.heydeskb.utility;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;

@Log
@Component
public class DeskUtil {

    public static boolean checkIfDeskAvailable(Long startReservation, Long endReservation, DeskDb deskDb) {

        boolean reservationIsAvailable = false;

        if (deskDb.getDeskReservations().isEmpty()) {
            reservationIsAvailable = true;
        } else {

            for (DeskReservationDb reservation : deskDb.getDeskReservations()) {
                if ((startReservation < reservation.getStartReservation() || startReservation > reservation.getEndReservation())
                        && (endReservation < reservation.getStartReservation()) || (endReservation > reservation.getEndReservation())) {
                    reservationIsAvailable = true;
                    log.log(Level.INFO, "Checking if available desk. Reservation: " + reservation.toString() + " / Reservation is possible: " + reservationIsAvailable);
                }
            }
        }
                log.log(Level.INFO, "checkIfDeskAvailable = " + reservationIsAvailable);
        return reservationIsAvailable;
    }
}
