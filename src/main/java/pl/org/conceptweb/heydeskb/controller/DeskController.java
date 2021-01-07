package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.converter.DeskConverter;
import pl.org.conceptweb.heydeskb.converter.DeskReservationConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;
import pl.org.conceptweb.heydeskb.utility.DeskUtil;
import pl.org.conceptweb.heydeskb.model.DeskReservation;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@RestController
@Log
@RequestMapping("/desk")
public class DeskController {

    @Autowired
    DeskDbRepository deskDbRepository;
    @Autowired
    DeskConverter deskConverter;
    @Autowired
    DeskReservationDbRepository deskReservationDbRepository;
    @Autowired
    DeskReservationConverter deskReservationConverter;
    @Autowired
    UserRepository userRepository;

    @PostMapping()
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper addDesk(@RequestBody Desk desk) {
        log.log(Level.INFO, "DeskController: addDesk: START / @RequestBody: " + desk);
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", Arrays.asList(deskDbRepository.save(deskConverter.deskToDeskDb(desk))));
        return httpResponseWrapper;
    }

    @GetMapping("/reservation")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper getAvailableDesksInPeriod(
            @RequestParam Long startReservation,
            @RequestParam Long endReservation,
            @RequestParam Long buildingId,
            @RequestParam Long floorId,
            @RequestParam Long roomId) {
        log.log(Level.INFO, "DeskController: getAvailableDesksInPeriod: START");
        List<DeskDb> availableList = new ArrayList<>();

        for (DeskDb deskDb : deskDbRepository.getDesksByBuildingAndFloorAndRoom(roomId, floorId, buildingId)) {
            if (DeskUtil.checkIfDeskAvailable(startReservation, endReservation, deskDb)) {
                availableList.add(deskDb);
            };
        }
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", availableList);
        log.log(Level.INFO, "DeskController: getAvailableDesksInPeriod: JUST BEFORE END");
        return httpResponseWrapper;
    }

    @PostMapping("/reservation")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper makeReservation(@RequestBody DeskReservation deskReservation, Principal principal) {
        log.log(Level.INFO, "DeskController: makeReservation: START @RequestBody: " + deskReservation);
        deskReservation.setUserId(userRepository.findByUserName(principal.getName()).get().getId());
        return new HttpResponseWrapper("ok", Arrays.asList(
                deskReservationConverter.deskReservationDbToDeskReservation(deskReservationDbRepository.save(
                        deskReservationConverter.deskReservationToDeskReservationDb(deskReservation))))
        );
    }

//    @GetMapping("/reservation/user")
     @GetMapping("/dupa")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper getAllReservationsByUser(@RequestParam Long userId) {
        log.log(Level.INFO, "DeskController: getAllReservationsByUser: userId: " + userId);
        return new HttpResponseWrapper("ok", deskReservationDbRepository.getAllByUser(userId));
    }
}
