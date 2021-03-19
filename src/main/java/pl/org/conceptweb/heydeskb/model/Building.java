
package pl.org.conceptweb.heydeskb.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Building {
    private Long id;
    private String name;
    private List<Long> floors;
    private Long companyDb;
    private Boolean isDeleted;
}
