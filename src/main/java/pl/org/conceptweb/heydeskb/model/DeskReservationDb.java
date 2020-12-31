package pl.org.conceptweb.heydeskb.model;

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
    //Long userId;
    @ManyToOne
    @JoinColumn(name = "deskId")
    private DeskDb deskId;
    private Long startReservation;
    private Long endReservation;
    
    public DeskReservationDb(){};

    public DeskReservationDb(Long id, DeskDb deskId, Long startReservation, Long endReservation) {
        this.id = id;
        this.deskId = deskId;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
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
}

