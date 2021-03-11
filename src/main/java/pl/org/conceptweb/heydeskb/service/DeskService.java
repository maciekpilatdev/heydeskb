package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.DeskConverter;
import pl.org.conceptweb.heydeskb.inputTest.InputTester;
import pl.org.conceptweb.heydeskb.inputTest.TextInputStrategy;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.security.SecurityAuthoritiesCheck;
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
import pl.org.conceptweb.heydeskb.model.RoomDb;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;

@Log
@Service
public class DeskService {

    @Autowired
    DeskConverter deskConverter;
    @Autowired
    DeskDbRepository deskDbRepository;
    @Autowired
    RoomDbRepository roomDbRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityAuthoritiesCheck securityAuthoritiesCheck;
    @Autowired
    UserService userService;
    @Autowired
    InputTester inputTester;
    @Autowired
    TextInputStrategy textInputStrategy;

    public HttpResponseWrapper addDesk(Desk desk) {

        desk.setIsDeleted(Boolean.FALSE);
        try {
            MethodResponse deskName = inputTester.runTest(textInputStrategy, desk.getName());
            if (deskName.getStatus().equals(Constans.ERROR)) {
                return new HttpResponseWrapper(Constans.ERROR, deskName.getMessage(), new ArrayList());
            } else if (!isNameUnique(desk)) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.NAME_NOT_UNIQUE_ERROR_MESSAGE, new ArrayList());
            } else if (!securityAuthoritiesCheck.hasAuthority(userService.getLogged().getUserName(), Constans.AUTHORITY_ADMIN)) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            } else {
                return new HttpResponseWrapper(Constans.OK, Constans.ADD_DESK_SUCCESS_MESSAGE, Arrays.asList(deskConverter.deskDbToDesk(deskDbRepository.save(deskConverter.deskToDeskDb(desk)))));
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskService: addDesk: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getDeskListByCompany(String loggedUserName) {
        try {
            return new HttpResponseWrapper(Constans.OK, Constans.GET_DESK_LIST_BY_COMPANY_SUCCESS_MESSAGE, deskConverter.desksDbToDesks(deskDbRepository.findByCompanyId(userRepository.findByUserName(loggedUserName).get().getCompanyDb().getId())));
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskService: getDeskListByCompany: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper deleteDesk(Long deskId, String loggedUserName) {
        DeskDb deskDb = deskDbRepository.getOne(deskId);
        try {
            if (securityAuthoritiesCheck.hasAuthority(loggedUserName, Constans.AUTHORITY_ADMIN)) {
                deskDb.setIsDeleted(true);
                return new HttpResponseWrapper(Constans.OK, Constans.DELETE_DESK_SUCCESS_MESSAGE, Arrays.asList(deskDbRepository.save(deskDb)));
            } else {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskService: deleteDesk: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public Boolean isNameUnique(Desk desk) {
        RoomDb roomDb = roomDbRepository.getOne(desk.getRoomId());
        return deskDbRepository.findAllByCompanyAndBuildingAndFloorAndRoomAndName(
                roomDb.getFloor().getBuilding().getCompanyDb().getId(),
                roomDb.getFloor().getBuilding().getId(),
                roomDb.getFloor().getId(),
                roomDb.getId(),
                desk.getName()
        ).isEmpty();
    }
}
