package pl.org.conceptweb.heydeskb.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;
import pl.org.conceptweb.heydeskb.model.HttpKeyValue;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@RestController
@RequestMapping("/stats")
public class PortalStatsController {

    @Autowired
    CompanyDbRepository companyDbRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeskReservationDbRepository deskReservationDbRepository;

    @GetMapping("/basic")
    public HttpResponseWrapper basicPortalStats() {
        List<HttpKeyValue> portalStats = new ArrayList();

        portalStats.add(new HttpKeyValue("numberOfCompanies", String.valueOf(companyDbRepository.findAll().size())));
        portalStats.add(new HttpKeyValue("numberOfUsers", String.valueOf(userRepository.findAll().size())));
        portalStats.add(new HttpKeyValue("totalNumberOfReservations", String.valueOf(deskReservationDbRepository.findAll().size())));
        portalStats.add(new HttpKeyValue("numberOfReservationsDaily", "not ready yet"));
        return new HttpResponseWrapper(Constans.OK, Constans.BASIC_PORTAL_STATS_SUCCESS_MESSAGE, portalStats);
    }
}
