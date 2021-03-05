package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.DeskConverter;
import pl.org.conceptweb.heydeskb.converter.DeskReservationConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;
import pl.org.conceptweb.heydeskb.utility.DeskUtil;
import pl.org.conceptweb.heydeskb.model.DeskReservation;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.service.DeskService;

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
    @Autowired
    DeskService deskService;
    
    @PostMapping()
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper addDesk(@RequestBody Desk desk) {
        return deskService.addDesk(desk);
    }

    @GetMapping("/company")
    public HttpResponseWrapper getDeskListByCompany(Principal principal) {
        return deskService.getDeskListByCompany(principal.getName());
    }

    @DeleteMapping()
    public HttpResponseWrapper deleteDesk(@RequestParam Long deskId, Principal principal) {
        return deskService.deleteDesk(deskId, principal.getName());
    }

    @DeleteMapping("/reservation")
    public HttpResponseWrapper cancelDeskReservation(@RequestParam Long deskReservationId) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            deskReservationDbRepository.deleteById(deskReservationId);
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.CANCEL_DESK_RESERVATION_SUCCESS_MESSAGE, new ArrayList());
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
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
        List<DeskDb> availableList = new ArrayList<>();
        HttpResponseWrapper httpResponseWrapper;
        try {
            for (DeskDb deskDb : deskDbRepository.findDesksByBuildingAndFloorAndRoom(roomId, floorId, buildingId)) {
                if (DeskUtil.checkIfDeskAvailable(startReservation, endReservation, deskDb)) {
                    availableList.add(deskDb);
                };
            }
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_AVAILABLE_DESK_IN_PERIOD_SUCCESS_MESSAGE, availableList);
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), availableList);
        }
        return httpResponseWrapper;
    }

    @PostMapping("/reservation")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper makeReservation(@RequestBody DeskReservation deskReservation, Principal principal) {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("", "", new ArrayList());

        try {
            deskReservation.setUserId(userRepository.findByUserName(principal.getName()).get().getId());
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, Arrays.asList(
                    deskReservationConverter.deskReservationDbToDeskReservation(deskReservationDbRepository.save(
                            deskReservationConverter.deskReservationToDeskReservationDb(deskReservation))))
            );
        } catch (Exception e) {
            new HttpResponseWrapper(Constans.ERROR, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, new ArrayList());
        }
        return httpResponseWrapper;
    }

    @GetMapping("/reservation/user")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper getAllReservationsByUser(Principal principal) {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("", "", new ArrayList());
        try {
            User loggedUser = userRepository.findByUserName(principal.getName()).get();
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_All_RESERVATIONS_BY_USER, deskReservationDbRepository.getAllByUser(loggedUser.getId()));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

}
