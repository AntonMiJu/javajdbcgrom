package hibernate.lesson3.dao;

import hibernate.lesson3.objects.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class HotelDAO {
    private SessionFactory sessionFactory;
    private GeneralDAO generalDAO = new GeneralDAO();

    private static final String findById = "SELECT * FROM HOTELS WHERE ID = ?";
    private static final String delete = "DELETE FROM HOTELS WHERE ID = ?";

    public Hotel findById(long id) { // поиск продукта по id
        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findById);
            query.addEntity(Hotel.class);
            query.setParameter(1, id);
            Hotel hotel = (Hotel) query.getSingleResult();

            return hotel;
        } catch (HibernateException e) {
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void delete(long id){
        generalDAO.delete(id,delete);
    }

    public Hotel save(Hotel hotel){
        return generalDAO.save(hotel);
    }

    public Hotel update(Hotel hotel){
        return generalDAO.update(hotel);
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
