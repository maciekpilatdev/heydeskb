package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.BuildingDb;
import pl.org.conceptweb.heydeskb.model.Building;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;

@Component
@Log
public class BuildingConverter {
    @Autowired
    BuildingDbRepository buildingDbRepository;
    @Autowired
    FloorConverter floorConverter;
    @Autowired
    CompanyDbRepository companyDbRepository;

    public Building buildingDbToBuilding(BuildingDb buildingDb) {
        return new Building(buildingDb.getId(), buildingDb.getName(), floorConverter.floorsDbToIdList(buildingDb.getFloors()), buildingDb.getCompanyDb().getId(), buildingDb.getIsDeleted());
    }

    public BuildingDb buildingToBuildingDb(Building building) {
        return new BuildingDb(building.getId(), building.getName(), floorConverter.idListToFloorsDb(building.getFloors()), companyDbRepository.getOne(building.getCompanyDb()), building.getIsDeleted());
    }

    public List<Building> buildingsDbToBuildings(List<BuildingDb> buildingsDb) {

        List<Building> buildings = new ArrayList();
        try {
            buildingsDb.forEach(buildingDb -> {
                buildings.add(buildingDbToBuilding(buildingDb));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "BuildingConverter: buildingsDbToBuildings: ERROR: ", e);
        }
        return buildings;
    }

    public List<BuildingDb> buildingsToBuildingsDb(List<Building> buildings) {

        List<BuildingDb> buildingsDb = new ArrayList();
        try {
            buildings.forEach(building -> {
                buildingsDb.add(buildingToBuildingDb(building));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "BuildingConverter: buildingsToBuildingsDb: ERROR: ", e);
        }
        return buildingsDb;
    }
    
     public List<Long> buildingDbToIdList(List<BuildingDb> buildingsDb) {
        List<Long> desks = new ArrayList();
        try {
            buildingsDb.forEach(deskDb -> {
                desks.add(deskDb.getId());
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "BuildingConverter: deskDbToIdList: ERROR: ", e);
        }
        return desks;
    }

    public List<BuildingDb> idListToBuildingDb(List<Long> IdList) {
        List<BuildingDb> desksDb = new ArrayList();
        try {
            IdList.forEach(desk -> {
                desksDb.add(buildingDbRepository.getOne(desk));
            });
        } catch (Exception e) {
            log.log(Level.WARNING, "BuildingConverter: idListToDeskDb: ERROR: ", e);
        }
        return desksDb;
    }
}
