package pl.org.conceptweb.heydeskb.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.org.conceptweb.heydeskb.model.DeskReservation;

@Data
@AllArgsConstructor
public class Desk {
    private Long id;
    private Long buildingId;
    private Long floorId;
    private Long roomId;
    private Long desksInRoom;
    private Boolean nextToWindow;
    private String name;
    private Boolean active;
    private Boolean isDeleted;
    private List<Long> deskReservations;
}
