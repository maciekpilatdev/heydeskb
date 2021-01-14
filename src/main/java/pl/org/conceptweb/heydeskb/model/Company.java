package pl.org.conceptweb.heydeskb.model;

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
private Long streetNumber;
private String postalCode;
private String city;
private String country;    
}
