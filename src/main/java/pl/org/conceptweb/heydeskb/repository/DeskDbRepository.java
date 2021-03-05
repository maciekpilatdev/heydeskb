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
            + "where r.id = ?1 and f.id = ?2 and b.id=?3 and d.isDeleted = false order by d.name")
    public List<DeskDb> findDesksByBuildingAndFloorAndRoom(Long roomId, Long floorId, Long buildingId);

    @Query("select d from DeskDb d "
            + "join d.roomDb r "
            + "join r.floor f "
            + "join f.building b "
            + "join b.companyDb c "
            + "where c.id = ?1 and d.isDeleted = false order by d.name")
    public List<DeskDb> findByCompanyId(Long companyId);
    
    @Query("select d from DeskDb d "
            + "join d.roomDb r "
            + "join r.floor f "
            + "join f.building b "
            + "join b.companyDb c "
            + "where c.id = ?1 and b.id = ?2 and f.id = ?3 and r.id = ?4 and d.name = ?5 and d.isDeleted = false order by d.name")
    public List<DeskDb> findAllByCompanyAndBuildingAndFloorAndRoomAndName(Long companyId, Long buildingId, Long floorId, Long roomId, String name);

}
