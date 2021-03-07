package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import lombok.extern.java.Log;
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
import pl.org.conceptweb.heydeskb.model.Room;
import pl.org.conceptweb.heydeskb.service.RoomService;

@Log
@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "https://heydeskb.herokuapp.com/", maxAge = 3600)
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping()
    public HttpResponseWrapper addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @GetMapping("/company")
    public HttpResponseWrapper getRoomListByCompany(Principal principal) {
        return roomService.getRoomListByCompany(principal.getName());
    }
    
    @DeleteMapping()
    public HttpResponseWrapper deleteRoom(@RequestParam Long roomId, Principal principal){        
        return roomService.deleteRoom(roomId, principal.getName());
    }
}
