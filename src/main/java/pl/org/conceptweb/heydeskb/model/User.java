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

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private boolean active;
    private String authoritys;
    private String jwt;
    private String jwtExpirationTime;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<DeskReservationDb> deskReservationsDb;
    @ManyToOne
    @JoinColumn()
    CompanyDb companyDb;
    
    public User(){}

    public User(String userName, String password, boolean active, String authoritys, String jwt, String jwtExpirationTime, Boolean isDeleted, CompanyDb companyDb) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.authoritys = authoritys;
        this.jwt = jwt;
        this.jwtExpirationTime = jwtExpirationTime;
        this.isDeleted = isDeleted;
        this.companyDb = companyDb;
    }
    
    public User(String userName, String password, boolean active, String authoritys, String jwt, String jwtExpirationTime, Boolean isDeleted, List<DeskReservationDb> deskReservationDb, CompanyDb companyDb) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.authoritys = authoritys;
        this.jwt = jwt;
        this.jwtExpirationTime = jwtExpirationTime;
        this.isDeleted = isDeleted;
        this.deskReservationsDb = deskReservationDb;
        this.companyDb = companyDb;
    }

    public User(Long id, String userName, String password, boolean active, String authoritys, String jwt, String jwtExpirationTime, Boolean isDeleted, List<DeskReservationDb> deskReservationDb, CompanyDb companyDb) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.authoritys = authoritys;
        this.jwt = jwt;
        this.jwtExpirationTime = jwtExpirationTime;
        this.isDeleted = isDeleted;
        this.deskReservationsDb = deskReservationDb;
        this.companyDb = companyDb;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", active=" + active + ", authoritys=" + authoritys + ", jwt=" + jwt + ", jwtExpirationTime=" + jwtExpirationTime + ", isDeleted=" + isDeleted + '}';
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(String authoritys) {
        this.authoritys = authoritys;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwtExpirationTime() {
        return jwtExpirationTime;
    }

    public void setJwtExpirationTime(String jwtExpirationTime) {
        this.jwtExpirationTime = jwtExpirationTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<DeskReservationDb> getDeskReservationsDb() {
        return deskReservationsDb;
    }

    public void setDeskReservationsDb(List<DeskReservationDb> deskReservationsDb) {
        this.deskReservationsDb = deskReservationsDb;
    }

    public CompanyDb getCompanyDb() {
        return companyDb;
    }

    public void setCompanyDb(CompanyDb companyDb) {
        this.companyDb = companyDb;
    }

}