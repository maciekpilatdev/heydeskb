package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.FloorConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.model.Floor;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@Log
@Service
public class FloorService {

    @Autowired
    FloorConverter floorConverter;
    @Autowired
    FloorDbRepository floorDbRepository;
    @Autowired
    UserRepository userRepository;

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
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOOR_LIST_BY_COMPANY_SUCCESS_MESSAGE, Arrays.asList(floorConverter.floorsDbToFloors(floorDbRepository.findByCompanyId(userRepository.findByUserName(loggedUserName).get().getCompanyDb().getId()))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
