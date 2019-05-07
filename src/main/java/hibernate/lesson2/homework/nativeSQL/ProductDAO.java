package hibernate.lesson2.homework.nativeSQL;

import hibernate.lesson2.homework.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductDAO {
    private SessionFactory sessionFactory;

    private static final String findById = "SELECT * FROM PRODUCT WHERE ID = ?";
    private static final String findByName = "SELECT * FROM PRODUCT WHERE NAME = ?";
    private static final String findByContainedName = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
    private static final String findByPrice = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
    private static final String findByNameSortedAsc = "SELECT * FROM PRODUCT ORDER BY NAME ASC";
    private static final String findByNameSortedDesc = "SELECT * FROM PRODUCT ORDER BY NAME DESC";
    private static final String findByPriceSortedDesc = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ? ORDER BY PRICE DESC";

    public Product findById(long id) { // поиск продукта по id
        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findById);
            query.addEntity(Product.class);
            query.setParameter(1, id);
            Product product = (Product) query.getSingleResult();

            return product;
        } catch (HibernateException e) {
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Product> findByName(String name) {
        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findByName);
            query.addEntity(Product.class);
            query.setParameter(1, name);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e) {
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по имени

    public List<Product> findByContainedName(String name) {
        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findByContainedName);
            query.addEntity(Product.class);
            query.setParameter(1, "%" + name + "%");
            List<Product> products = (List<Product> ) query.list();

            return products;
        } catch (HibernateException e) {
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов, которые в своем имени содержать слово name

    public List<Product> findByPrice(int price, int delta) {
        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findByPrice);
            query.addEntity(Product.class);
            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e) {
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по вилке цен price+-delta включительно

    public List<Product> findByNameSortedAsc() {
        try(Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findByNameSortedAsc);
            query.addEntity(Product.class);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name

    public List<Product> findByNameSortedDesc() {
        try(Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findByNameSortedDesc);
            query.addEntity(Product.class);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name

    public List<Product> findByPriceSortedDesc(int price, int delta) {
        try(Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(findByPriceSortedDesc);
            query.addEntity(Product.class);
            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);
            List<Product> products = query.list();

            return products;
        } catch (HibernateException e){
            System.err.println("Something gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    } // поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
