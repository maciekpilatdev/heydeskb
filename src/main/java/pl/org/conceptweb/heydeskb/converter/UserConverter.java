package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.model.UserTrans;

@Component
@Log
public class UserConverter {

    @Autowired
    DeskReservationConverter deskReservationConverter;
    @Autowired
    CompanyConverter companyConverter;

    public UserTrans userToUserTrans(User user) {

        return new UserTrans(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.isActive(),
                user.getAuthoritys(),
                user.getJwt(),
                user.getJwtExpirationTime(),
                user.getIsDeleted(),
                deskReservationConverter.deskReservationsDbToDeskReservations(user.getDeskReservationDb()),
                companyConverter.companyDbToCompany(user.getCompanyDb())
        );
    }

    public User userTransToUser(UserTrans userTrans) {
        return new User(
                userTrans.getId(),
                userTrans.getUserName(),
                userTrans.getPassword(),
                userTrans.isActive(),
                userTrans.getAuthoritys(),
                userTrans.getJwt(),
                userTrans.getJwtExpirationTime(),
                userTrans.getIsDeleted(),
                deskReservationConverter.deskReservationsToDeskReservationsDb(userTrans.getDeskReservation()),
                companyConverter.companyToCompanyDb(userTrans.getCompany())
        );
    }

    public List<UserTrans> usersToUsersTrans(List<User> users) {
        List<UserTrans> usersTrans = new ArrayList();
        try {
            users.forEach(user -> {
                usersTrans.add(userToUserTrans(user));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "UserConverter: usersToUsersTrans: " + e);
        }
        return null;
    }

    public List<User> usersTransToUsers(List<UserTrans> usersTrans) {
        List<User> users = new ArrayList();
        try {
            usersTrans.forEach(userTrans -> {
                users.add(userTransToUser(userTrans));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "UserConverter: usersTransToUsers: " + e);
        }
        return null;
    }
}
