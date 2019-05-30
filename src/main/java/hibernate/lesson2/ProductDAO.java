package hibernate.lesson2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
    private SessionFactory sessionFactory;

    public Product save(Product product) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()){
            tr = session.getTransaction();
            tr.begin();

            session.save(product);

            tr.commit();
            return product;
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return null;
    }

    public Product update(Product product){
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()){
            tr = session.getTransaction();
            tr.begin();

            session.update(product);

            tr.commit();
            return product;
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return null;
    }

    public Product delete(Product product){
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()){
            tr = session.getTransaction();
            tr.begin();

            session.delete(product);

            tr.commit();
            return product;
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return null;
    }

    public void saveAll(List<Product> products) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()){
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products)
                session.save(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
    }

    public void updateAll(List<Product> products){
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()){
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products)
                session.update(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
    }

    public void deleteAll(List<Product> products){
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()){
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products)
                session.delete(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
