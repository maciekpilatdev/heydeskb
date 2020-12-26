package pl.org.conceptweb.heydeskb.converter;

import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.ReservationPeriodDb;
import pl.org.conceptweb.heydeskb.model.ReservationPeriod;

@Component
public class ReservationPeriodConverter {
    
    public ReservationPeriodDb ReservationPeriodDbToReservationPeriod(ReservationPeriod reservationPeriod){
        return new ReservationPeriodDb(
                reservationPeriod.getId(),
                reservationPeriod.getStart(),
                reservationPeriod.getEnd(),
                reservationPeriod.getUserId());
    }
    
    public ReservationPeriod ReservationPeriodDbToReservationPeriodDb(ReservationPeriodDb reservationPeriodDb){
        return new ReservationPeriod(
                reservationPeriodDb.getId(),
                reservationPeriodDb.getStart(),
                reservationPeriodDb.getEnd(),
                reservationPeriodDb.getUserId());
    }
}
