
package pl.org.conceptweb.heydeskb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeskReservation {
    private Long id;
    //private Long userId;
    private Long deskId;
    private Long startReservation;
    private Long endReservation;    
}
