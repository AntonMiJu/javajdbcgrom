package jdbc.lesson4;

import jdbc.lesson3.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    public static void save(List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products,connection);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException{
        try(PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (?,?,?,?)")) {
            connection.setAutoCommit(false);

            for (Product product : products) {
                prepareStatement.setLong(1, product.getId());
                prepareStatement.setString(2, product.getName());
                prepareStatement.setString(3, product.getDescription());
                prepareStatement.setInt(4, product.getPrice());

                int response = prepareStatement.executeUpdate();
                System.out.println("Save was finished with result " + response);
            }
            connection.commit();
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
    }

    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
