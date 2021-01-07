package pl.org.conceptweb.heydeskb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DeskReservationDb {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "deskId")
    private DeskDb deskId;
    private Long startReservation;
    private Long endReservation;
    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;
    
    public DeskReservationDb(){};

    public DeskReservationDb(Long id, DeskDb deskId, Long startReservation, Long endReservation) {
        this.id = id;
        this.deskId = deskId;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
    }

    public DeskReservationDb(Long id, DeskDb deskId, Long startReservation, Long endReservation, User user) {
        this.id = id;
        this.deskId = deskId;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeskDb getDeskId() {
        return deskId;
    }

    public void setDeskId(DeskDb deskId) {
        this.deskId = deskId;
    }

    public Long getStartReservation() {
        return startReservation;
    }

    public void setStartReservation(Long startReservation) {
        this.startReservation = startReservation;
    }

    public Long getEndReservation() {
        return endReservation;
    }

    public void setEndReservation(Long endReservation) {
        this.endReservation = endReservation;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}

