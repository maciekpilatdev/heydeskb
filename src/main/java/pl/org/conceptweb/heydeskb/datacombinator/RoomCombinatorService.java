package pl.org.conceptweb.heydeskb.datacombinator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.UserService;
import pl.org.conceptweb.heydeskb.datacombinatormodel.RoomCombinatorModel;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;

@Log
@Component
public class RoomCombinatorService {

    @Autowired
    UserService userService;
    @Autowired
    RoomDbRepository roomDbRepository;
    @Autowired
    FloorDbRepository floorDbRepository;
    @Autowired
    BuildingDbRepository buildingDbRepository;

    public HttpResponseWrapper getListDataByCompany() {
        HttpResponseWrapper httpResponseWrapper;
        try {
            Long companyId = userService.getLogged().getCompanyDb().getId();
            List<RoomCombinatorModel> roomCombinatorList = new ArrayList();
            roomDbRepository.findByCompanyId(companyId).forEach(room -> {
                floorDbRepository.findByCompany(companyId).forEach(floor -> {
                    buildingDbRepository.findByCompany(companyId).forEach(building -> {
                        if (room.getFloor().getId().equals(floor.getId()) && floor.getBuilding().getId().equals(building.getId())) {
                            roomCombinatorList.add(new RoomCombinatorModel(room.getId().toString(), room.getName(), floor.getName(), building.getName(), null, null));
                        }
                    });
                });
            });
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_ROOMS_SUCCESS_MESSAGE, roomCombinatorList);
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
