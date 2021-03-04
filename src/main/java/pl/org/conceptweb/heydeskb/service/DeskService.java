package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.DeskConverter;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.security.SecurityAuthoritiesCheck;
import pl.org.conceptweb.heydeskb.model.Desk;

@Log
@Service
public class DeskService {

    @Autowired
    DeskConverter deskConverter;
    @Autowired
    DeskDbRepository deskDbRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityAuthoritiesCheck securityAuthoritiesCheck;
    
    public HttpResponseWrapper addDesk(Desk desk, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        desk.setIsDeleted(Boolean.FALSE);
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_DESK_SUCCESS_MESSAGE, Arrays.asList(deskConverter.deskDbToDesk(deskDbRepository.save(deskConverter.deskToDeskDb(desk)))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
    
    public HttpResponseWrapper getDeskListByCompany(String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_DESK_LIST_BY_COMPANY_SUCCESS_MESSAGE, deskConverter.desksDbToDesks(deskDbRepository.findByCompanyId(userRepository.findByUserName(loggedUserName).get().getCompanyDb().getId())));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
    
    public HttpResponseWrapper deleteDesk(Long deskId, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        DeskDb deskDb = deskDbRepository.getOne(deskId);
        try {
            if (securityAuthoritiesCheck.hasAuthority(loggedUserName, "ADMIN")) {
                deskDb.setIsDeleted(true);
//                deskDbRepository.save(deskDb);
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_DESK_SUCCESS_MESSAGE, Arrays.asList(deskDbRepository.save(deskDb)));
            } else {
                httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }    
}
