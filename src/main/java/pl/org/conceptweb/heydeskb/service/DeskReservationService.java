package pl.org.conceptweb.heydeskb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;

@Service
public class DeskReservationService {
    @Autowired
    DeskReservationDbRepository deskReservationDbRepository;

 public DeskReservationDb deleteDeskReservationById(Long deskReservationId){    
     DeskReservationDb deskReservationDb = deskReservationDbRepository.getOne(deskReservationId);
     deskReservationDbRepository.deleteById(deskReservationId);
     return deskReservationDb; 
 }
    
}
