package jdbc.lesson3.homework.task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    public List<Product> findProductsByPrice(int price, int delta) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?");

            prepareStatement.setInt(1, price - delta);
            prepareStatement.setInt(2, price + delta);

            ResultSet resultSet = prepareStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
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

    public List<Product> findProductsByName(String word) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE NAME = ?");

            prepareStatement.setString(1,word);

            ResultSet resultSet = prepareStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
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

    public List<Product> findProductsWithEmptyDescription() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL");

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
