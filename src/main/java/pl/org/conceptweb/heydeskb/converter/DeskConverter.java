package pl.org.conceptweb.heydeskb.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.model.DeskDb;
import pl.org.conceptweb.heydeskb.repository.DeskDbRepository;
import pl.org.conceptweb.heydeskb.repository.RoomDbRepository;

@Component
public class DeskConverter {

    @Autowired
    RoomDbRepository roomDbRepository;
    
    public Desk deskDbToDesk(DeskDb deskDb) {

        return new Desk(
                deskDb.getId(),
                deskDb.getBuildingId(),
                deskDb.getFloorId(),
                deskDb.getRoomId().getId(),
                deskDb.getDesksInRoom(),
                deskDb.getNextToWindow(),
                deskDb.getDescription(),
                deskDb.getActive(),
                deskDb.getDeleted());
    }

    public DeskDb DeskToDeskDb(Desk desk) {
        return new DeskDb(
                desk.getId(),
                desk.getBuildingId(),
                desk.getFloorId(),
                roomDbRepository.getOne(desk.getRoomId()),
                desk.getDesksInRoom(),
                desk.getNextToWindow(),
                desk.getDescription(),
                desk.getActive(),
                desk.getDeleted());
    }
}
