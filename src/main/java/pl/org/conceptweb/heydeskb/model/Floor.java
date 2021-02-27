package pl.org.conceptweb.heydeskb.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {

    private Long id;
    private String name;
    private Long building;
    private List<Long> rooms;
}
