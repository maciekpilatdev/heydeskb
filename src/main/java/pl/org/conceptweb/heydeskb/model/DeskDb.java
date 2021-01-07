package pl.org.conceptweb.heydeskb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;
    private Long buildingId;
    private Long floorId;
    @ManyToOne
    @JoinColumn()
    private RoomDb roomDb;    
    private Long desksInRoom;
    private Boolean nextToWindow;
    private String description;
    private Boolean active;
    private Boolean deleted;
    @JsonIgnore
    @OneToMany(mappedBy = "deskId")
    private List<DeskReservationDb> deskReservations;

    @Override
    public String toString() {
        return "DeskDb{" + "deskId=" + getId() + ", buildingId=" + getBuildingId() + ", floorId=" + getFloorId() + ", roomId=" + getRoomDb() + ", desksInRoom=" + getDesksInRoom() + ", nextToWindow=" + getNextToWindow() + '}';
    }

    public DeskDb(){};

    public DeskDb(Long id, Long buildingId, Long floorId, RoomDb roomDb, Long desksInRoom, Boolean nextToWindow, String description, Boolean active, Boolean deleted, List<DeskReservationDb> deskReservations) {
        this.id = id;
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.roomDb = roomDb;
        this.desksInRoom = desksInRoom;
        this.nextToWindow = nextToWindow;
        this.description = description;
        this.active = active;
        this.deleted = deleted;
        this.deskReservations = deskReservations;
    }
    
    public DeskDb(Long buildingId, Long floorId, RoomDb roomDb, Long desksInRoom, Boolean nextToWindow, String description, Boolean active, Boolean deleted, List<DeskReservationDb> deskReservations) {
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.roomDb = roomDb;
        this.desksInRoom = desksInRoom;
        this.nextToWindow = nextToWindow;
        this.description = description;
        this.active = active;
        this.deleted = deleted;
        this.deskReservations = deskReservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoomDb getRoomDb() {
        return roomDb;
    }

    public void setRoomDb(RoomDb roomDb) {
        this.roomDb = roomDb;
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