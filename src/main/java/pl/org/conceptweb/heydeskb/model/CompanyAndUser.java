package pl.org.conceptweb.heydeskb.model;

import lombok.Data;

@Data
public class CompanyAndUser {
    Company company;
    String userName;
    String password;
    String repeatedPassword;
}
