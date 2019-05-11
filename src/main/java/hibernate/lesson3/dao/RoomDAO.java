package hibernate.lesson3.dao;

import hibernate.lesson3.objects.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class RoomDAO {
    private SessionFactory sessionFactory;
    private GeneralDAO generalDAO = new GeneralDAO();

    private static final String findById = "SELECT * FROM ROOMS WHERE ID = ?";
    private static final String delete = "DELETE FROM ROOMS WHERE ID = ?";

    public Room findById(long id) { // поиск продукта по id
        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findById);
            query.addEntity(Room.class);
            query.setParameter(1, id);
            Room room = (Room) query.getSingleResult();

            return room;
        } catch (HibernateException e) {
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void delete(long id){
        generalDAO.delete(id, delete);
    }

    public Room save(Room room){
        return generalDAO.save(room);
    }

    public Room update(Room room){
        return generalDAO.update(room);
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
