package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.DeskReservationConverter;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.utility.DeskUtil;
import pl.org.conceptweb.heydeskb.model.DeskReservation;
import pl.org.conceptweb.heydeskb.model.User;

@Service
public class DeskReservationService {

    @Autowired
    DeskReservationDbRepository deskReservationDbRepository;
    @Autowired
    DeskReservationConverter deskReservationConverter;
    @Autowired
    DeskDbRepository deskDbRepository;
    @Autowired
    UserService userService;
    
    public HttpResponseWrapper getAllByUser() {
        HttpResponseWrapper httpResponseWrapper;
        try {
            User loggedUser = userService.getLogged();
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_All_RESERVATIONS_BY_USER, deskReservationDbRepository.getAllByUser(loggedUser.getId()));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper makeReservation(DeskReservation deskReservation) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            deskReservation.setUserId(userService.getLogged().getId());
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, Arrays.asList(
                    deskReservationConverter.deskReservationDbToDeskReservation(deskReservationDbRepository.save(
                            deskReservationConverter.deskReservationToDeskReservationDb(deskReservation))))
            );
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper getAvailableDesksInPeriod(Long startReservation, Long endReservation, Long buildingId, Long floorId, Long roomId) {
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

    public HttpResponseWrapper deleteById(Long deskReservationId) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            DeskReservationDb deskReservationDb = deskReservationDbRepository.getOne(deskReservationId);
            deskReservationDbRepository.deleteById(deskReservationId);
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_DESK_RESERVATION_BY_ID_SUCCESS_MESSAGE, Arrays.asList(deskReservationConverter.deskReservationDbToDeskReservation(deskReservationDb)));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper getAllByCompany(Long companyId) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_ALL_BY_COMPANY_SUCCESS_MESSAGE, deskReservationConverter.deskReservationsDbToDeskReservations(deskReservationDbRepository.getAllByCompany(companyId)));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, new ArrayList());
        }
        return httpResponseWrapper;
    }

}
