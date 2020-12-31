package pl.org.conceptweb.heydeskb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="desk_db")
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
    private RoomDb roomId;    
    private Long desksInRoom;
    private Boolean nextToWindow;
    private String description;
    private Boolean active;
    private Boolean deleted;
    @OneToMany(mappedBy = "deskId")
    private List<DeskReservationDb> deskReservations;

    @Override
    public String toString() {
        return "DeskDb{" + "deskId=" + deskId + ", buildingId=" + buildingId + ", floorId=" + floorId + ", roomId=" + roomId + ", desksInRoom=" + desksInRoom + ", nextToWindow=" + nextToWindow + '}';
    }

    public DeskDb(){};
    
    public DeskDb(Long buildingId, Long floorId, RoomDb roomId, Long desksInRoom, Boolean nextToWindow, String description, Boolean active, Boolean deleted) {
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.roomId = roomId;
        this.desksInRoom = desksInRoom;
        this.nextToWindow = nextToWindow;
        this.description=description;
        this.active=active;
        this.deleted=deleted;
    }

    public DeskDb(Long deskId, Long buildingId, Long floorId, RoomDb roomId, Long desksInRoom, Boolean nextToWindow, String description, Boolean active, Boolean deleted) {
        this.deskId = deskId;
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.roomId = roomId;
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

    public RoomDb getRoomId() {
        return roomId;
    }

    public void setRoomId(RoomDb roomId) {
        this.roomId = roomId;
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

    public List<DeskReservationDb> getDeskReservations() {
        return deskReservations;
    }

    public void setDeskReservations(List<DeskReservationDb> deskReservations) {
        this.deskReservations = deskReservations;
    }
    
}