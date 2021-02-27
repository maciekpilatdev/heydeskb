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
public class FloorDb {
@Id
@GeneratedValue
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name="buildingId")
    private BuildingDb building;
    @OneToMany(mappedBy = "floor")
    @JsonIgnore
    private List<RoomDb> rooms;
    
 public FloorDb(){};
 
 public FloorDb(Long id, String name){
     this.id=id;
     this.name=name;
 }

    public FloorDb(Long id, String name, BuildingDb building, List<RoomDb> rooms) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.rooms = rooms;
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

    public BuildingDb getBuilding() {
        return building;
    }

    public void setBuilding(BuildingDb building) {
        this.building = building;
    }

    public List<RoomDb> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDb> rooms) {
        this.rooms = rooms;
    }
    
}