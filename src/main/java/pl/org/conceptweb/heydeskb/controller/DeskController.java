package pl.org.conceptweb.heydeskb.controller;

import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.converter.DeskConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.model.ReservationPeriod;

@RestController
@Log
@RequestMapping("/desk")
public class DeskController {

    @Autowired
    DeskDbRepository deskDbRepository;
    @Autowired
    DeskConverter deskConverter;

    @PostMapping()
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper addDesk(@RequestBody Desk desk) {  
        log.log(Level.WARNING, "DESK: " + desk.getNextToWindow());
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", Arrays.asList(deskDbRepository.save(deskConverter.DeskToDeskDb(desk))));
        return httpResponseWrapper;
    }
    
    @PostMapping("/reservation")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper makeReservation(@RequestBody ReservationPeriod reservationPeriod){
        return null;
    }
}
