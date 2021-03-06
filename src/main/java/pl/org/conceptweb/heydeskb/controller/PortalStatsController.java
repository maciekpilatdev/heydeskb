package pl.org.conceptweb.heydeskb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.PortalStatsService;

@RestController
@RequestMapping("/stats")
public class PortalStatsController {

    @Autowired
    PortalStatsService portalStatsService;

    @GetMapping("/basic")
    public HttpResponseWrapper basicPortalStats() {
        return portalStatsService.getBasic();
    }
}
