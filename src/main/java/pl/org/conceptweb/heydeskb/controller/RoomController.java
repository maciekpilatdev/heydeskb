package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Room;
import pl.org.conceptweb.heydeskb.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping()
    public HttpResponseWrapper addRoom(@RequestBody Room room, Principal principal) {
        return roomService.addRoom(room, principal.getName());
    }
}
