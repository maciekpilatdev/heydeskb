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
            + "where c.id = ?1")
    public List<BuildingDb> getAllByCompany(Long companyId);
}

//"select d from DeskDb d "
//        + "join d.roomDb r "
//        + "join r.floor f "
//        + "join f.building b "
//        + "where r.id = ?1 and f.id = ?2 and b.id=?3"
