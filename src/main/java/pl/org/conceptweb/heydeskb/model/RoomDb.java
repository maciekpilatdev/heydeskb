package pl.org.conceptweb.heydeskb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class RoomDb {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "floorId")
    private FloorDb floor;
    @JsonIgnore
    @OneToMany(mappedBy = "roomDb")
    public List<DeskDb> desks;

    public RoomDb() {
    }

    ;
 
 public RoomDb(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomDb(Long id, String name, FloorDb floor, List<DeskDb> desks) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.desks = desks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FloorDb getFloor() {
        return floor;
    }

    public void setFloor(FloorDb floor) {
        this.floor = floor;
    }

    public List<DeskDb> getDesks() {
        return desks;
    }

    public void setDesks(List<DeskDb> desks) {
        this.desks = desks;
    }

}
