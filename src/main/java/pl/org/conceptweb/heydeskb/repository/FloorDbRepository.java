package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.FloorDb;

@Repository
public interface FloorDbRepository extends JpaRepository<FloorDb, Long>{

    public List<FloorDb> findByBuildingId(Long buildingId);
    
    @Query("select f from FloorDb f "
            + "join f.building b "
            + "join b.companyDb c "
            + "where c.id = ?1 and f.isDeleted = false")
    public List<FloorDb> findByCompanyId(Long companyId);
        
}
