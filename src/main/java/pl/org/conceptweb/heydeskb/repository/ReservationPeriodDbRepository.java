package pl.org.conceptweb.heydeskb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.ReservationPeriodDb;

@Repository
public interface ReservationPeriodDbRepository extends JpaRepository<ReservationPeriodDb, Long>{
    
}
