package pl.org.conceptweb.heydeskb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.PortalStatsService;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "https://heydeskb.herokuapp.com/", maxAge = 3600)
public class PortalStatsController {

    @Autowired
    PortalStatsService portalStatsService;

    @GetMapping("/basic")
    public HttpResponseWrapper basicPortalStats() {
        return portalStatsService.getBasic();
    }
}
