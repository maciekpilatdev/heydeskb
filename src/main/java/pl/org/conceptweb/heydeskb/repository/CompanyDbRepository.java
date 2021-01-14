package pl.org.conceptweb.heydeskb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.org.conceptweb.heydeskb.model.CompanyDb;

@Repository
public interface CompanyDbRepository extends JpaRepository<CompanyDb, Long>{
    
}
