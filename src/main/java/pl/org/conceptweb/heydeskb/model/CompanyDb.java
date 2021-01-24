package pl.org.conceptweb.heydeskb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CompanyDb {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String mail;
    private String phone;
    private String street;
    private Long streetNumber;
    private String postalCode;
    private String city;
    private String country;
    @OneToMany(mappedBy = "companyDb")
    @JsonIgnore
    List<BuildingDb> buildingDb;
    @OneToMany(mappedBy = "companyDb")
    @JsonIgnore
    List<User> users;

    public CompanyDb() {
    }

    public CompanyDb(Long id, String name, String mail, String phone, String street, Long streetNumber, String postalCode, String city, String country, List<BuildingDb> buildingDb, List<User> users) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.buildingDb = buildingDb;
        this.users = users;
    }

    public CompanyDb(String name, String mail, String phone, String street, Long streetNumber, String postalCode, String city, String country, List<BuildingDb> buildingDb, List<User> users) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.buildingDb = buildingDb;
        this.users = users;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Long streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<BuildingDb> getBuildingDb() {
        return buildingDb;
    }

    public void setBuildingDb(List<BuildingDb> buildingDb) {
        this.buildingDb = buildingDb;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
