package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.RoomDb;

@Repository
public interface RoomDbRepository extends JpaRepository<RoomDb, Long> {

    @Query(value
            = "select r from RoomDb r "
            + "join r.floor f "
            + "join f.building b "
            + "where f.id = ?1 and b.id = ?2"
    )
    public List<RoomDb> findByFloorAndBuilding(Long floorId, Long buildingId);
}
