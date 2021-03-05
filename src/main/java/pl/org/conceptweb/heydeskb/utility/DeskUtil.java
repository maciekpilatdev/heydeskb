package pl.org.conceptweb.heydeskb.utility;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;

@Log
@Component
public class DeskUtil {
    
    

    public static Boolean checkTimeFormat(Long startReservation, Long endReservation) {
        Boolean isTimeFormatCorrect = true;
        if ((startReservation >= endReservation)) {
            isTimeFormatCorrect = false;
        }
        return isTimeFormatCorrect;
    }

    public static Boolean checkIfDeskAvailable(Long startReservation, Long endReservation, DeskDb deskDb) {

        boolean reservationIsAvailable = false;

        if (deskDb.getDeskReservations().isEmpty()) {
            reservationIsAvailable = true;
        } else {

            for (DeskReservationDb reservation : deskDb.getDeskReservations()) {
                if ((startReservation >= reservation.getEndReservation() && endReservation > reservation.getEndReservation()) || (startReservation < reservation.getStartReservation() && endReservation <= reservation.getEndReservation())) {
                    reservationIsAvailable = true;
                }
                log.log(Level.WARNING, "11111 desk: " + deskDb.getName() + " / StartReservation: " + Instant.ofEpochMilli(reservation.getStartReservation()).atZone(ZoneId.of("UTC")).toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME) + " / EndReservation: " + Instant.ofEpochMilli(reservation.getEndReservation()).atZone(ZoneId.of("UTC")).toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME) + " / reservationIsAvailable: " + reservationIsAvailable);
            }
        }
        return reservationIsAvailable;
    }
}
