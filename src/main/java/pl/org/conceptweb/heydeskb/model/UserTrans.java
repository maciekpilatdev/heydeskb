package pl.org.conceptweb.heydeskb.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserTrans {

    private Long id;
    private String userName;
    private String password;
    private boolean active;
    private String authoritys;
    private String jwt;
    private String jwtExpirationTime;
    private Boolean isDeleted;
    private List<Long> deskReservations;
    private Long company;
}
