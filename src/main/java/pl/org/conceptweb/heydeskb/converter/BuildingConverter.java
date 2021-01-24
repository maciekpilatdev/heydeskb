package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.BuildingDb;
import pl.org.conceptweb.heydeskb.model.Building;

@Component
@Log
public class BuildingConverter {

    public Building buildingDbToBuilding(BuildingDb buildingDb) {
        return new Building(buildingDb.getId(), buildingDb.getName(), buildingDb.getFloors(), buildingDb.getCompanyDb());
    }

    public BuildingDb buildingToBuildingDb(Building building) {
        return new BuildingDb(building.getId(), building.getName(), building.getFloors(), building.getCompanyDb());
    }

    public List<Building> buildingsDbToBuildings(List<BuildingDb> buildingsDb) {

        List<Building> buildings = new ArrayList();
        try {
            buildingsDb.forEach(buildingDb -> {
                buildings.add(buildingDbToBuilding(buildingDb));
            });
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "BuildingConverter: buildingsDbToBuildings: ERROR: " + e);
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
            log.log(java.util.logging.Level.WARNING, "BuildingConverter: buildingsToBuildingsDb: ERROR: " + e);
        }
        return buildingsDb;
    }
}
