package pl.org.conceptweb.heydeskb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.DeskReservationConverter;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.DeskReservationService;

@RestController
@RequestMapping("/deskerservation")
public class DeskReservationController {

    @Autowired
    DeskReservationDbRepository deskReservationDbRepository;
    @Autowired
    DeskReservationConverter deskReservationConverter;
    @Autowired
    DeskReservationService deskReservationService;

    @GetMapping("/getallbycompany")
    public HttpResponseWrapper getAllByCompany(@RequestParam Long companyId) {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("", "", new ArrayList());
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_ALL_BY_COMPANY_SUCCESS_MESSAGE, deskReservationConverter.deskReservationsDbToDeskReservations(deskReservationDbRepository.getAllByCompany(companyId)));
        } catch (Exception e) {
            new HttpResponseWrapper(Constans.ERROR, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, new ArrayList());
        }
        return httpResponseWrapper;
    }

    @DeleteMapping("/delete")
    public HttpResponseWrapper deleteDeskReservationById(@RequestParam Long deskReservationId) {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("", "", new ArrayList());
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_DESK_RESERVATION_BY_ID_SUCCESS_MESSAGE, Arrays.asList(deskReservationConverter.deskReservationDbToDeskReservation(deskReservationService.deleteDeskReservationById(deskReservationId))));
        } catch (Exception e) {
            new HttpResponseWrapper(Constans.ERROR, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, new ArrayList());
        }
        return httpResponseWrapper;
    }
}
