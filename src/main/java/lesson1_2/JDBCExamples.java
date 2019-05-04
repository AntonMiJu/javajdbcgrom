package lesson1_2;

import java.sql.*;

public class JDBCExamples {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
//            boolean res = statement.execute("INSERT INTO PRODUCT VALUES (2,'TOY111','FOR CHILDREN',60)");
//            System.out.println(res);

//            boolean res = statement.execute("DELETE FROM PRODUCT WHERE NAME = 'TOY111'");
//            System.out.println(res);

//            int response = statement.executeUpdate("INSERT INTO PRODUCT VALUES (5,'CAR','FOR CHILDREN',770)");
//            System.out.println(response);

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME = 'CAR'");
            System.out.println(response);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
