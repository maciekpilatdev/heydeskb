package pl.org.conceptweb.heydeskb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyAndUser {
    Company company;
    String userName;
    String password;
    String repeatedPassword;
}
