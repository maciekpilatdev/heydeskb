package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.model.UserTrans;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;

@Component
@Log
public class UserConverter {

    @Autowired
    DeskReservationConverter deskReservationConverter;
    @Autowired
    CompanyConverter companyConverter;
    @Autowired
    CompanyDbRepository companyDbRepository;

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
                deskReservationConverter.deskReservationsDbToIdList(user.getDeskReservationsDb()),
                user.getCompanyDb().getId()
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
                deskReservationConverter.idListToDeskReservationsDb(userTrans.getDeskReservations()),
                companyDbRepository.getOne(userTrans.getCompany())
        );
    }

    public List<UserTrans> usersToUsersTrans(List<User> users) {
        List<UserTrans> usersTrans = new ArrayList();
        try {
            users.forEach(user -> {
                usersTrans.add(userToUserTrans(user));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "ERROR: UserConverter: usersToUsersTrans: " + e);
        }
        return usersTrans;
    }

    public List<User> usersTransToUsers(List<UserTrans> usersTrans) {
        List<User> users = new ArrayList();
        try {
            usersTrans.forEach(userTrans -> {
                users.add(userTransToUser(userTrans));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "ERROR: UserConverter: usersTransToUsers: " + e);
        }
        return users;
    }
}
