package pl.org.conceptweb.heydeskb.utility;

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

@Log
@Service
public class FloorService {
@Autowired
FloorConverter floorConverter;
@Autowired
FloorDbRepository floorDbRepository;

    public HttpResponseWrapper addFloor(Floor floor, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;        
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_FLOOR_SUCCESS_MESSAGE, Arrays.asList(floorConverter.floorDbToFloor(floorDbRepository.save(floorConverter.floorToFloorDb(floor)))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
