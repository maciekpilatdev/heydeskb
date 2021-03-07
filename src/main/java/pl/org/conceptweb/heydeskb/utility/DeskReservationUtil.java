package pl.org.conceptweb.heydeskb.utility;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
import pl.org.conceptweb.heydeskb.model.DeskReservation;

@Log
@Component
public class DeskReservationUtil {

//    public MethodResponse ifPossible(DeskReservation deskReservation) {
//
//        MethodResponse methodResponse = new MethodResponse();
//        StringBuilder sb = new StringBuilder();
//        sb.setLength(0);
//
//        if (!ifTimeFormatGood(deskReservation.getStartReservation(), deskReservation.getEndReservation())) {
//            methodResponse.setStatus(Constans.ERROR);
//            methodResponse.setMessage(sb.append(Constans.IF_PASSWORD_IDENTICAL_ERROR_MESSAGE).toString());
//        }
//
//        if (sb.length() == 0) {
//            methodResponse.setStatus(Constans.OK);
//            methodResponse.setMessage(sb.append(Constans.IF_USERNAME_IS_PROPER_SUCCESS_MESSAGE).toString());
//        }
//        return methodResponse;
//    }

    public static Boolean ifTimeFormatGood(Long startReservation, Long endReservation) {
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
