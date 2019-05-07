package hibernate.lesson2;

import hibernate.lesson1.Product;
import hibernate.lesson1.homework.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        product.setName("table");
        product.setDescription("grey");
        product.setPrice(70);

        Product product1 = new Product();
        product1.setName("table111");
        product1.setDescription("grey");
        product1.setPrice(70);

        Product product2 = new Product();
        product2.setName("table222");
        product2.setDescription("grey");
        product2.setPrice(70);

        Product product3 = new Product();
        product3.setName("table333");
        product3.setDescription("grey");
        product3.setPrice(70);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

    }
}
