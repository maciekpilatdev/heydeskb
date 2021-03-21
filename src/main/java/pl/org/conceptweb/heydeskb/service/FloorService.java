package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.FloorConverter;
import pl.org.conceptweb.heydeskb.inputTest.InputTester;
import pl.org.conceptweb.heydeskb.inputTest.TextInputStrategy;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.model.Floor;
import pl.org.conceptweb.heydeskb.model.FloorDb;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.security.SecurityAuthoritiesCheck;

@Log
@Service
public class FloorService {

    @Autowired
    FloorConverter floorConverter;
    @Autowired
    FloorDbRepository floorDbRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityAuthoritiesCheck securityAuthoritiesCheck;
    @Autowired
    UserService userService;
    @Autowired
    FloorService floorService;
    @Autowired
    InputTester inputTester;
    @Autowired
    TextInputStrategy textInputStrategy;

    public HttpResponseWrapper add(Floor floor) {
        try {
            MethodResponse floorName = inputTester.runTest(textInputStrategy, floor.getName());
            if (floorName.getStatus().equals(Constans.ERROR)) {
                return new HttpResponseWrapper(Constans.ERROR, floorName.getMessage(), new ArrayList());
            } else if (!isNameUnique(floor)) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.NAME_NOT_UNIQUE_ERROR_MESSAGE, new ArrayList());
            } else if (!securityAuthoritiesCheck.hasAuthority(userService.getLogged().getUserName(), Constans.AUTHORITY_ADMIN)) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            } else {
                return new HttpResponseWrapper(Constans.OK, Constans.ADD_FLOOR_SUCCESS_MESSAGE, Arrays.asList(floorConverter.floorDbToFloor(floorDbRepository.save(floorConverter.floorToFloorDb(floor)))));
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: FloorService: addFloor: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getListByCompany() {
        try {
            return new HttpResponseWrapper(Constans.OK, Constans.GET_FLOOR_LIST_BY_COMPANY_SUCCESS_MESSAGE, floorConverter.floorsDbToFloors(floorDbRepository.findByCompany(userRepository.findByUserName(userService.getLogged().getUserName()).get().getCompanyDb().getId())));
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: FloorService: getFloorListByCompany: ", e);
            return new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
    }

    public HttpResponseWrapper delete(Long floorId) {
        FloorDb floorDb = floorDbRepository.getOne(floorId);
        try {
            if (securityAuthoritiesCheck.hasAuthority(userService.getLogged().getUserName(), Constans.AUTHORITY_ADMIN)) {
                floorDb.setIsDeleted(true);
                floorDbRepository.save(floorDb);
                return new HttpResponseWrapper(Constans.OK, Constans.DELETE_FLOOR_SUCCESS_MESSAGE, new ArrayList());
            } else {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: FloorService: deleteFloor: ", e);
            return new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
    }

    public Boolean isNameUnique(Floor floor) {
        return floorDbRepository.findAllByCompanyAndBuildingAndName(userService.getLogged().getCompanyDb().getId(), floor.getBuilding(), floor.getName()).isEmpty();
    }
}
