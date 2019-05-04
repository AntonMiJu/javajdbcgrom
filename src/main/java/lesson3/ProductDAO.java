package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    public Product save(Product product) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (?,?,?,?)");

            prepareStatement.setLong(1,product.getId());
            prepareStatement.setString(2,product.getName());
            prepareStatement.setString(3,product.getDescription());
            prepareStatement.setInt(4,product.getPrice());

            int response = prepareStatement.executeUpdate();

            System.out.println("Save was finished with result "+response);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProducts() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");

            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public Product update(Product product) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?");

            prepareStatement.setString(1,product.getName());
            prepareStatement.setString(2,product.getDescription());
            prepareStatement.setInt(3,product.getPrice());
            prepareStatement.setLong(4,product.getId());

            int response = prepareStatement.executeUpdate();

            System.out.println("Save was finished with result "+response);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public void delete(long id) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID FROM PRODUCT");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID = ?");

            preparedStatement.setLong(1,id);
            int response;

            while (resultSet.next()){
                if (resultSet.getLong(1)== id){
                    response = preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
