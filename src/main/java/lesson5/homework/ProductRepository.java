package lesson5.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProductRepository {
    public void save(Product product){
        Session session = createSessionFactory().openSession();

        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();

        session.close();
    }

    public void delete(long id){
        Session session = createSessionFactory().openSession();
        session.getTransaction().begin();

        session.delete(session.get(Product.class, id));

        session.getTransaction().commit();

        session.close();
    }

    public void update(Product product){
        Session session = createSessionFactory().openSession();
        session.getTransaction().begin();

        session.update(product);

        session.getTransaction().commit();

        session.close();
    }

    public SessionFactory createSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }
}
