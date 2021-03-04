package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.DeskDb;

@Repository
public interface DeskDbRepository extends JpaRepository<DeskDb, Long> {

    @Query("select d from DeskDb d "
            + "join d.roomDb r "
            + "join r.floor f "
            + "join f.building b "
            + "where r.id = ?1 and f.id = ?2 and b.id=?3 and d.isDeleted = false")
    public List<DeskDb> getDesksByBuildingAndFloorAndRoom(Long roomId, Long floorId, Long buildingId);

    @Query("select d from DeskDb d "
            + "join d.roomDb r "
            + "join r.floor f "
            + "join f.building b "
            + "join b.companyDb c "
            + "where c.id = ?1 and d.isDeleted = false")
    public List<DeskDb> findByCompanyId(Long companyId);

}
