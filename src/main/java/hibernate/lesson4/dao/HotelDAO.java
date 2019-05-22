package hibernate.lesson4.dao;

import hibernate.lesson4.objects.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel>{

    public HotelDAO() {
        setParameterOfClass(Hotel.class);
        setGetAll(getAll);
        setDelete(delete);
    }

    private static final String delete = "DELETE FROM HOTELS WHERE ID = ?";
    private static final String getAll = "SELECT * FROM HOTELS";
    private static final String findByName = "SELECT * FROM HOTELS WHERE NAME = ?";
    private static final String findByCity = "SELECT * FROM HOTELS WHERE CITY = ?";

    public Hotel findByName(String name){
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.createQuery(findByName,Hotel.class).setParameter(1,name).getSingleResult();
        } catch (HibernateException e){
            System.err.println("Find by name is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Hotel findByCity(String city){
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.createQuery(findByCity,Hotel.class).setParameter(1,city).getSingleResult();
        } catch (HibernateException e){
            System.err.println("Find by name is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Hotel save(Hotel hotel) {
        return super.save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return super.update(hotel);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public Hotel findById(long id) {
        return super.findById(id);
    }

    @Override
    public List<Hotel> getAll() {
        return super.getAll();
    }
}
