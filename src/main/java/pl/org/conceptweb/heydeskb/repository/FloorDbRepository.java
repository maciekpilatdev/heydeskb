package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.FloorDb;

@Repository
public interface FloorDbRepository extends JpaRepository<FloorDb, Long> {

    public List<FloorDb> findByBuildingId(Long buildingId);

    @Query("select f from FloorDb f "
            + "join f.building b "
            + "join b.companyDb c "
            + "where c.id = ?1 and f.isDeleted = false order by f.name")
    public List<FloorDb> findByCompany(Long companyId);

    @Query("select f from FloorDb f "
            + "join f.building b "
            + "join b.companyDb c "
            + "where c.id = ?1 and b.id =?2 and f.isDeleted = false order by f.name")
    public List<FloorDb> findAllByCompanyAndBuilding(Long companyId, Long buildingId);

    @Query("select f from FloorDb f "
            + "join f.building b "
            + "join b.companyDb c "
            + "where c.id = ?1 and b.id =?2 and f.name =?3 and f.isDeleted = false order by f.name")
    public List<FloorDb> findAllByCompanyAndBuildingAndName(Long companyId, Long buildingId, String name);

}
