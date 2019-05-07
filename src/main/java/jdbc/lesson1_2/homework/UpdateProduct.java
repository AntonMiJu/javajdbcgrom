package jdbc.lesson1_2.homework;

import java.sql.*;

public class UpdateProduct {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    public void increasePrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            int response = statement.executeUpdate("UPDATE PRODUCT SET PRICE = PRICE + 100 WHERE PRICE < 970");
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void changeDescription() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement(); Statement statementUpdate = connection.createStatement()) {
            try (ResultSet response = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 100")) {
                while (response.next()) {
                    String description = response.getString(3);
                    int id = response.getInt(1);
                    String subDescription = new String();
                    String result;
                    if (description.lastIndexOf('.') != -1)
                        subDescription = description.substring(0, description.lastIndexOf('.'));
                        result = subDescription.substring(0, subDescription.lastIndexOf('.')+1);
                    System.out.println(description);
                    System.out.println(id);
                    String sql = "UPDATE PRODUCT SET DESCRIPTION = '" + result + "' WHERE ID = " + id;
                    int res = statementUpdate.executeUpdate(sql);
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
