package pl.org.conceptweb.heydeskb.datacombinator;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.FloorDbRepository;
import pl.org.conceptweb.heydeskb.service.UserService;
import pl.org.conceptweb.heydeskb.datacombinatormodel.FloorCombinatorModel;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;

@Log
@Component
public class FloorCombinatorService {

    @Autowired
    FloorDbRepository floorDbRepository;
    @Autowired
    BuildingDbRepository buildingDbRepository;
    @Autowired
    UserService userService;

    public HttpResponseWrapper getListDataByCompany() {
        HttpResponseWrapper httpResponseWrapper;
        try {
            Long companyId = userService.getLogged().getCompanyDb().getId();
            List<FloorCombinatorModel> floorListDataList = new ArrayList();
            floorDbRepository.findByCompany(companyId).forEach(floor -> {
                buildingDbRepository.findByCompany(companyId).forEach(building -> {
                    if (floor.getBuilding().getId().equals(building.getId())) {
                        floorListDataList.add(new FloorCombinatorModel(floor.getId().toString(), floor.getName(), building.getName(), null, null));
                    }
                });
            });
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_FLOORS_SUCCESS_MESSAGE, floorListDataList);
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
