package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.RoomConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Room;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;

@Service
public class RoomService {

    @Autowired
    RoomConverter roomConverter;
    @Autowired
    RoomDbRepository roomDbRepository;

    public HttpResponseWrapper addRoom(Room room, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_ROOM_SUCCESS_MESSAGE, Arrays.asList(roomConverter.roomDbToRoom(roomDbRepository.save(roomConverter.roomToRoomDb(room)))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
