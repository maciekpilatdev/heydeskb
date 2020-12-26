package pl.org.conceptweb.heydeskb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class ReservationPeriodDb {
@Id
@GeneratedValue
    private Long id;
    private Long start;
    private Long end;
    private Long userId;

    public ReservationPeriodDb(){};
    
    public ReservationPeriodDb(Long start, Long end, Long userId){
        this.start = start;
        this.end = end;
        this.userId = userId;
    };

        public ReservationPeriodDb(Long id, Long start, Long end, Long userId){
        this.id = id;
        this.start = start;
        this.end = end;
        this.userId = userId;
    };
    
    @Override
    public String toString() {
        return "ReservationPeriodDb{" + "id=" + id + ", start=" + start + ", end=" + end + ", userId=" + userId + '}';
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
