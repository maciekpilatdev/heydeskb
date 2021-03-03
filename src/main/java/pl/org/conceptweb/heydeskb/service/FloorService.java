package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.FloorConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.model.Floor;
import pl.org.conceptweb.heydeskb.model.FloorDb;
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

    public HttpResponseWrapper addFloor(Floor floor, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_FLOOR_SUCCESS_MESSAGE, Arrays.asList(floorConverter.floorDbToFloor(floorDbRepository.save(floorConverter.floorToFloorDb(floor)))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper getFloorListByCompany(String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOOR_LIST_BY_COMPANY_SUCCESS_MESSAGE, floorConverter.floorsDbToFloors(floorDbRepository.findByCompanyId(userRepository.findByUserName(loggedUserName).get().getCompanyDb().getId())));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper deleteFloor(Long floorId, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        FloorDb floorDb = floorDbRepository.getOne(floorId);
        try {
            if (securityAuthoritiesCheck.hasAuthority(loggedUserName, "ADMIN")) {
                floorDb.setIsDeleted(true);
                floorDbRepository.save(floorDb);
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_FLOOR_SUCCESS_MESSAGE, new ArrayList());
            } else {
                httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
