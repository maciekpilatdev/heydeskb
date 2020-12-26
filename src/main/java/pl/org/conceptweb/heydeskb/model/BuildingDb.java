package pl.org.conceptweb.heydeskb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Table
public class BuildingDb {
@Id
@GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<FloorDb> floors;
    
 public BuildingDb(){};
 
 public BuildingDb(Long id, String name){
     this.id=id;
     this.name=name;
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

    public List<FloorDb> getFloors() {
        return floors;
    }

    public void setFloors(List<FloorDb> floors) {
        this.floors = floors;
    }

    
    
 
}
