package hibernate.lesson4.controller;

import hibernate.lesson4.objects.Filter;
import hibernate.lesson4.objects.Room;
import hibernate.lesson4.objects.Session;
import hibernate.lesson4.objects.UserType;
import hibernate.lesson4.service.RoomService;

import java.util.List;

public class RoomController {
    private Session session = new Session();
    private RoomService roomService = new RoomService();

    public Room addRoom(Room room) throws Exception{
        validateLoginAdmin();
        return roomService.addRoom(room);
    }

    public void deleteRoom(long id) throws Exception{
        validateLoginAdmin();
        roomService.deleteRoom(id);
    }

    public List<Room> findRooms(Filter filter) throws Exception{
        validateLogin();
        return roomService.findRooms(filter);
    }

    private void validateLogin() throws Exception{
        if (session.getCurrentUser() == null)
            throw new Exception("You must be logined.");
    }

    private void validateLoginAdmin() throws Exception{
        if (session.getCurrentUser() == null)
            throw new Exception("You must be logined.");
        if (session.getCurrentUser().getUserType().equals(UserType.USER))
            throw new Exception("You must have admin rights");
    }
}
