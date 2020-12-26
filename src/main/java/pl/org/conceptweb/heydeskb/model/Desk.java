package pl.org.conceptweb.heydeskb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Desk {
    private Long id;
    private Long buildingId;
    private Long floorId;
    private Long roomId;
    private Long desksInRoom;
    private Boolean nextToWindow;
    private String description;
    private Boolean active;
    private Boolean deleted;
}
