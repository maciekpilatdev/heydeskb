package pl.org.conceptweb.heydeskb.datacombinatormodel;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FloorCombinatorModel {
    private String id;
    private String name;
    private String building;
    private List<String> rooms;
    private String isDeleted;    
}
