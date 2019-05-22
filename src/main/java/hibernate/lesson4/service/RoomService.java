package hibernate.lesson4.service;

import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.objects.Filter;
import hibernate.lesson4.objects.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public Room addRoom(Room room) throws Exception{
        if (roomDAO.findById(room.getId())!= null)
            throw new Exception("Room "+ room.getId()+" already in DB.");
        return roomDAO.save(room);
    }

    public void deleteRoom(long id){
        roomDAO.delete(id);
    }

    public List<Room> findRooms(Filter filter){
        List<Room> result = new ArrayList<>();
        for (Room el : roomDAO.getAll()){
            if (validateRoomByFilter(el,filter))
                result.add(el);
        }
        return result;
    }

    private boolean validateRoomByFilter(Room room, Filter filter){
        return room.getNumberOfGuests() == filter.getNumberOfGuests() && room.getPrice() == filter.getPrice() && room.getBreakfastIncluded() == filter.getBreakfastIncluded() && room.getPetsAllowed() == filter.getPetsAllowed() && room.getDateAvailableFrom().equals(filter.getDateAvailableFrom());
    }
}
