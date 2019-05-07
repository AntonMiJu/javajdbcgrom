package hibernate.lesson1;

import hibernate.lesson1.homework.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Demo {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        Product product = new Product();
        product.setId(999);
        product.setName("table");
        product.setDescription("grey");
        product.setPrice(70);

//        save(product);
        productRepository.delete(99);
    }

    public static void save(Product product){
        Session session = createSessionFactory().openSession();

        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();

        System.out.println("Done");

        session.close();
    }

    private static SessionFactory createSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }
}
