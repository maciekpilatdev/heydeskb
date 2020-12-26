package pl.org.conceptweb.heydeskb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationPeriod {

    private Long id;
    private Long start;
    private Long end;
    private Long userId;
}
