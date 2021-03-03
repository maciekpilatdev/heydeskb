package pl.org.conceptweb.heydeskb.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    public Long id;
    public String name;
    public Long floor;
    public List<Long> desks;
    private Boolean isDeleted;
}
