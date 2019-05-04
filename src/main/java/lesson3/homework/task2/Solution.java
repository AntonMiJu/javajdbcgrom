package lesson3.homework.task2;

import java.sql.*;

public class Solution {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    //275957
    public void testSavePerfomance(){
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEST_SPEED VALUES(?,?,?)");
            long start = System.currentTimeMillis();
            for (int i = 1; i<1000; i++){
                preparedStatement.setInt(1,i);
                preparedStatement.setString(2, String.valueOf(i));
                preparedStatement.setInt(3,i);
                int response = preparedStatement.executeUpdate();
            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //286429
    public void testDeleteByIdPerfomance(){
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM TEST_SPEED WHERE ID = ?");
            long start = System.currentTimeMillis();
            for (int i = 1; i<1000; i++){
                preparedStatement.setInt(1,i);
                int response = preparedStatement.executeUpdate();
            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //330
    public void testDeletePerfomance(){
        try(Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            long start = System.currentTimeMillis();
            int response = statement.executeUpdate("DELETE FROM TEST_SPEED");
            System.out.println(System.currentTimeMillis() - start);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //250726
    public void testSelectByIdPerfomance(){
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TEST_SPEED WHERE ID = ?");
            long start = System.currentTimeMillis();
            for (int i = 1; i<1000; i++){
                preparedStatement.setInt(1,i);
                int response = preparedStatement.executeUpdate();
            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //316
    public void testSelectPerfomance(){
        try(Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            long start = System.currentTimeMillis();
            int response = statement.executeUpdate("SELECT * FROM TEST_SPEED");
            System.out.println(System.currentTimeMillis() - start);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
