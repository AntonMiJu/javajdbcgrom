package hibernate.lesson4.dao;

import hibernate.lesson4.objects.Room;

import java.util.List;

public class RoomDAO extends GeneralDAO<Room>{
    public RoomDAO() {
        setParameterOfClass(Room.class);
        setGetAll(getAll);
        setDelete(delete);
    }

    private static final String getAll = "SELECT * FROM ROOMS";
    private static final String delete = "DELETE FROM ROOMS WHERE ID = ?";

    @Override
    public Room save(Room room) {
        return super.save(room);
    }

    @Override
    public Room update(Room room) {
        return super.update(room);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public Room findById(long id) {
        return super.findById(id);
    }

    @Override
    public List<Room> getAll() {
        return super.getAll();
    }
}
