package hibernate.lesson2.homework.hql;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    private SessionFactory sessionFactory;

    private static final String findById = "FROM Product WHERE ID = :id";
    private static final String findByName = "FROM Product WHERE NAME = :name";
    private static final String findByContainedName = "FROM Product WHERE NAME LIKE :name";
    private static final String findByPrice = "FROM Product WHERE PRICE BETWEEN :start AND :end";
    private static final String findByNameSortedAsc = "FROM Product ORDER BY NAME ASC";
    private static final String findByNameSortedDesc = "FROM Product ORDER BY NAME DESC";
    private static final String findByPriceSortedDesc = "FROM Product WHERE PRICE BETWEEN :start AND :end ORDER BY PRICE DESC";

    public Product findById(long id) { // поиск продукта по id
        try (Session session = createSessionFactory().openSession()){

            Query query = session.createQuery(findById);
            query.setParameter("id",id);
            Product product = (Product) query.getSingleResult();

            return product;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByName(String name){
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery(findByName);
            query.setParameter("name", name);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по имени

    public List<Product> findByContainedName(String name){
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery(findByContainedName);
            query.setParameter("name", "%" + name + "%");
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов, которые в своем имени содержать слово name

    public List<Product> findByPrice(int price, int delta){
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery(findByPrice);
            query.setParameter("start", price-delta);
            query.setParameter("end", price+delta);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по вилке цен price+-delta включительно

    public List<Product> findByNameSortedAsc(){
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery(findByNameSortedAsc);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name

    public List<Product> findByNameSortedDesc(){
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery(findByNameSortedDesc);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name

    public List<Product> findByPriceSortedDesc(int price, int delta){
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery(findByPriceSortedDesc);
            query.setParameter("start", price-delta);
            query.setParameter("end", price+delta);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен

    private SessionFactory createSessionFactory(){
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
