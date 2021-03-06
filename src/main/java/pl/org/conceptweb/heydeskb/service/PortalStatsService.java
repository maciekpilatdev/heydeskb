package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.HttpKeyValue;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;
import pl.org.conceptweb.heydeskb.repository.DeskReservationDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@Log
@Service
public class PortalStatsService {

@Autowired
    CompanyDbRepository companyDbRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeskReservationDbRepository deskReservationDbRepository;

    public HttpResponseWrapper getBasic() {
        List<HttpKeyValue> portalStats = new ArrayList();
        portalStats.add(new HttpKeyValue("numberOfCompanies", String.valueOf(companyDbRepository.findAll().size())));
        portalStats.add(new HttpKeyValue("numberOfUsers", String.valueOf(userRepository.findAll().size())));
        portalStats.add(new HttpKeyValue("totalNumberOfReservations", String.valueOf(deskReservationDbRepository.findAll().size())));
        portalStats.add(new HttpKeyValue("numberOfReservationsDaily", "not ready yet"));
        return new HttpResponseWrapper(Constans.OK, Constans.BASIC_PORTAL_STATS_SUCCESS_MESSAGE, portalStats);
    }    
}
