package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.DeskReservationConverter;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.DeskReservationDb;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.utility.DeskReservationUtil;
import pl.org.conceptweb.heydeskb.model.DeskReservation;
import pl.org.conceptweb.heydeskb.model.User;

@Log
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
        try {
            User loggedUser = userService.getLogged();
            return new HttpResponseWrapper(Constans.OK, Constans.GET_All_RESERVATIONS_BY_USER, deskReservationDbRepository.getAllByUser(loggedUser.getId()));
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskReservationService: getAllByUser: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper makeReservation(DeskReservation deskReservation) {
        try {
            deskReservation.setUserId(userService.getLogged().getId());
            return new HttpResponseWrapper(Constans.OK, Constans.MAKE_RESERVATION_SUCCESS_MESSAGE, Arrays.asList(
                    deskReservationConverter.deskReservationDbToDeskReservation(deskReservationDbRepository.save(
                            deskReservationConverter.deskReservationToDeskReservationDb(deskReservation))))
            );
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskReservationService: makeReservation: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getAvailableDesksInPeriod(Long startReservation, Long endReservation, Long buildingId, Long floorId, Long roomId) {
        List<DeskDb> availableList = new ArrayList<>();
        try {
            if (DeskReservationUtil.ifTimeFormatGood(startReservation, endReservation)) {
                for (DeskDb deskDb : deskDbRepository.findDesksByBuildingAndFloorAndRoom(roomId, floorId, buildingId)) {
                    if (DeskReservationUtil.checkIfDeskAvailable(startReservation, endReservation, deskDb)) {
                        availableList.add(deskDb);
                    };
                }
                return new HttpResponseWrapper(Constans.OK, Constans.GET_AVAILABLE_DESK_IN_PERIOD_SUCCESS_MESSAGE, availableList);
            } else {
                return new HttpResponseWrapper(Constans.OK, Constans.TIME_FORMAT_ERROR_MESSAGE, availableList);
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskReservationService: getAvailableDesksInPeriod: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, availableList);
        }
    }

    public HttpResponseWrapper deleteById(Long deskReservationId) {
        try {
            DeskReservationDb deskReservationDb = deskReservationDbRepository.getOne(deskReservationId);
            deskReservationDbRepository.deleteById(deskReservationId);
            return new HttpResponseWrapper(Constans.OK, Constans.DELETE_DESK_RESERVATION_BY_ID_SUCCESS_MESSAGE, Arrays.asList(deskReservationConverter.deskReservationDbToDeskReservation(deskReservationDb)));
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskReservationService: deleteById: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getAllByCompany(Long companyId) {
        try {
            return new HttpResponseWrapper(Constans.OK, Constans.GET_ALL_BY_COMPANY_SUCCESS_MESSAGE, deskReservationConverter.deskReservationsDbToDeskReservations(deskReservationDbRepository.getAllByCompany(companyId)));
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: DeskReservationService: getAllByCompany: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }
}
