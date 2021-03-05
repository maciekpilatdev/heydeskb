package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.BuildingDb;

@Repository
public interface BuildingDbRepository extends JpaRepository<BuildingDb, Long> {

    @Query(value = "select b from BuildingDb b "
            + "join b.companyDb c "
            + "where c.id = ?1 and b.isDeleted = false order by b.name")
    public List<BuildingDb> getAllByCompany(Long companyId);
    
      @Query(value = "select b from BuildingDb b "
            + "join b.companyDb c "
            + "where c.id = ?1 and b.name = ?2 and b.isDeleted = false order by b.name")
    public List<BuildingDb> getAllByCompanyAndName(Long companyId, String name);
    
}


