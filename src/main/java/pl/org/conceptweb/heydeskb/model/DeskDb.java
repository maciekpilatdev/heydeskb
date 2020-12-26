package pl.org.conceptweb.heydeskb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="deskdb")
@Entity()
public class DeskDb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deskId;
    private Long buildingId;
    private Long floorId;
    
    @ManyToOne
    @JoinColumn(name = "roomId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RoomDb room;
    
    private Long desksInRoom;
    private Boolean nextToWindow;
    private String description;
    private Boolean active;
    private Boolean deleted;

    @Override
    public String toString() {
        return "DeskDb{" + "deskId=" + deskId + ", buildingId=" + buildingId + ", floorId=" + floorId + ", room=" + room + ", desksInRoom=" + desksInRoom + ", nextToWindow=" + nextToWindow + '}';
    }

    public DeskDb(){};
    
    public DeskDb(Long buildingId, Long floorId, RoomDb room, Long desksInRoom, Boolean nextToWindow, String description, Boolean active, Boolean deleted) {
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.room = room;
        this.desksInRoom = desksInRoom;
        this.nextToWindow = nextToWindow;
        this.description=description;
        this.active=active;
        this.deleted=deleted;
    }

    public DeskDb(Long deskId, Long buildingId, Long floorId, RoomDb room, Long desksInRoom, Boolean nextToWindow, String description, Boolean active, Boolean deleted) {
        this.deskId = deskId;
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.room = room;
        this.desksInRoom = desksInRoom;
        this.nextToWindow = nextToWindow;
        this.description=description;
        this.active=active;
        this.deleted=deleted;
    }
   
    public Long getId() {
        return deskId;
    }

    public void setId(Long id) {
        this.deskId = deskId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public RoomDb getRoom() {
        return room;
    }

    public void setRoom(RoomDb room) {
        this.room = room;
    }

    public Long getDesksInRoom() {
        return desksInRoom;
    }

    public void setDesksInRoom(Long desksInRoom) {
        this.desksInRoom = desksInRoom;
    }

    public Boolean getNextToWindow() {
        return nextToWindow;
    }

    public void setNextToWindow(Boolean nextToWindow) {
        this.nextToWindow = nextToWindow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }  
}