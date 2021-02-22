package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;

@Repository
public interface DeskReservationDbRepository extends JpaRepository<DeskReservationDb, Long>{

    @Query(value = "select * from  DESK_RESERVATION_DB dr "
            + "join DESK_DB d on (d.ID = dr.DESK_ID)"
            + " where dr.USER_ID = ?1", nativeQuery = true)
    List<DeskReservationDb> getAllByUser(Long userId);
    
    @Query("select r from DeskReservationDb r "
            + "join r.user u "
            + "join u.companyDb c "
            + "where c.id = ?1")
    List<DeskReservationDb> getAllByCompany(Long companyId);
}