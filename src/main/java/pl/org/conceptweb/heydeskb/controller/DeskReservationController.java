package pl.org.conceptweb.heydeskb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.DeskReservationService;
import pl.org.conceptweb.heydeskb.model.DeskReservation;

@RestController
@RequestMapping("/deskerservation")
@CrossOrigin(origins = "https://heydeskb.herokuapp.com/", maxAge = 3600)
public class DeskReservationController {

    @Autowired
    DeskReservationService deskReservationService;

    @GetMapping("/user")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper getAllReservationsByUser() {
        return deskReservationService.getAllByUser();
    }

    @PostMapping()
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper makeReservation(@RequestBody DeskReservation deskReservation) {
        return deskReservationService.makeReservation(deskReservation);
    }

    @GetMapping("/availabledesks")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper getAvailableDesksInPeriod(
            @RequestParam Long startReservation,
            @RequestParam Long endReservation,
            @RequestParam Long buildingId,
            @RequestParam Long floorId,
            @RequestParam Long roomId) {
        return deskReservationService.getAvailableDesksInPeriod(startReservation, endReservation, buildingId, floorId, roomId);
    }

    @DeleteMapping()
    public HttpResponseWrapper deleteById(@RequestParam Long deskReservationId) {
        return deskReservationService.deleteById(deskReservationId);
    }

    @GetMapping("/getallbycompany")
    public HttpResponseWrapper getAllByCompany(@RequestParam Long companyId) {
        return deskReservationService.getAllByCompany(companyId);
    }

}
