package hibernate.lesson4.dao;

import hibernate.lesson4.objects.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDAO extends GeneralDAO<User>{
    public UserDAO() {
        setParameterOfClass(User.class);
        setDelete(delete);
        setGetAll(getAll);
    }

    private static final String getAll = "SELECT * FROM USERS";
    private static final String delete = "DELETE FROM USERS WHERE ID = ?";
    private static final String findByName = "SELECT * FROM USERS WHERE NAME = ?";

    public User findByName(String name){
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.createQuery(findByName,User.class).setParameter(1,name).getSingleResult();
        } catch (HibernateException e){
            System.err.println("Find by name is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User save(User user) {
        return super.save(user);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public User findById(long id) {
        return super.findById(id);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }
}
