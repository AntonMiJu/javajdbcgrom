package jdbc.lesson4.homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    private StorageDAO storageDAO = new StorageDAO();
    private Connection connection = null;

    public File save(File file)  throws SQLException {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO FILES VALUES (?,?,?,?,?)");

            prepareStatement.setLong(1, file.getId());
            prepareStatement.setString(2, file.getName());
            prepareStatement.setString(3, file.getFormat());
            prepareStatement.setLong(4, file.getSize());
            if (file.getStorage() == null)
                prepareStatement.setNull(5, Types.LONGNVARCHAR);
            else
                prepareStatement.setLong(5, file.getStorage().getId());

            int response = prepareStatement.executeUpdate();

            System.out.println("Save was finished with result " + response);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return file;
    }

    public File update(File file) throws SQLException{
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement prepareStatement = connection.prepareStatement("UPDATE FILES SET NAME_F = ?, FORMAT_F = ?, SIZE_F = ?, STORAGE_ID = ? WHERE ID = ?");

            prepareStatement.setString(1, file.getName());
            prepareStatement.setString(2, file.getFormat());
            prepareStatement.setLong(3, file.getSize());
            if (file.getStorage() == null)
                prepareStatement.setNull(4, Types.LONGNVARCHAR);
            else
                prepareStatement.setLong(4, file.getStorage().getId());
            prepareStatement.setLong(5, file.getId());

            int response = prepareStatement.executeUpdate();

            System.out.println("Update was finished with result " + response);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return file;
    }

    public void delete(long id) throws SQLException{
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery("SELECT ID FROM FILES");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FILES WHERE ID = ?");

            preparedStatement.setLong(1, id);
            int response = 0;

            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    response = preparedStatement.executeUpdate();
                }
            }
            System.out.println("Delete was finished with result " + response);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public File findById(long id) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FILES");

            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    return new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), storageDAO.findById(resultSet.getLong(5)));
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public List<File> findFileByStorage(Storage storage){
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILES WHERE STORAGE_ID = ?");
            preparedStatement.setLong(1,storage.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<File> files = new ArrayList<>();

            while (resultSet.next())
                files.add(new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), storageDAO.findById(resultSet.getLong(5))));
            return files;
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }
}
