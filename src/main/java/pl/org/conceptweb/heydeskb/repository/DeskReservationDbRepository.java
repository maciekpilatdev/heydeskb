package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;

@Repository
public interface DeskReservationDbRepository extends JpaRepository<DeskReservationDb, Long>{

    @Query("select dr from DeskReservationDb dr where dr.user = ?1")
    List<DeskReservationDb> getAllByUser(Long userId);
}
