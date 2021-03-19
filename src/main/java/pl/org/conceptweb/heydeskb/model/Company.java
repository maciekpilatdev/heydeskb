package pl.org.conceptweb.heydeskb.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Company {
private Long id;
private String name;
private String mail;
private String phone;
private String street;
private String streetNumber;
private String postalCode;
private String city;
private String country;
private List<Long> buildings;
private List<Long> usersTrans;
}
