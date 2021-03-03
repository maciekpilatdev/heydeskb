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
public class BuildingDb {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<FloorDb> floors;
    @ManyToOne
    @JoinColumn()
    CompanyDb companyDb;
    private Boolean isDeleted;

    public BuildingDb() {
    }

    ;

    public BuildingDb(Long id, String name, List<FloorDb> floors, CompanyDb companyDb, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.floors = floors;
        this.companyDb = companyDb;
        this.isDeleted = isDeleted;
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

    public CompanyDb getCompanyDb() {
        return companyDb;
    }

    public void setCompanyDb(CompanyDb companyDb) {
        this.companyDb = companyDb;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}
