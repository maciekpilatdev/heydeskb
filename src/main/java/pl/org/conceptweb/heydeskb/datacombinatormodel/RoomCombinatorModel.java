package pl.org.conceptweb.heydeskb.datacombinatormodel;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomCombinatorModel {

    public String id;
    public String name;
    public String floor;
    public String building;
    public List<String> desks;
    public String isDeleted;
}
