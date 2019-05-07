package jdbc.lesson4.homework;

import java.sql.*;

public class StorageDAO {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    public Storage save(Storage storage) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO FILES VALUES (?,?,?,?)");

            prepareStatement.setLong(1, storage.getId());
            prepareStatement.setString(2, storage.getFormatsInString());
            prepareStatement.setString(3, storage.getStorageCountry());
            prepareStatement.setLong(4, storage.getStorageMaxSize());

            int response = prepareStatement.executeUpdate();

            System.out.println("Save was finished with result " + response);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return storage;
    }

    public Storage update(Storage storage) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("UPDATE FILES SET FORMATS_SUPPORTED = ?, COUNTRY = ?, MAX_SIZE = ? WHERE ID = ?");

            prepareStatement.setString(1, storage.getFormatsInString());
            prepareStatement.setString(2, storage.getStorageCountry());
            prepareStatement.setLong(3, storage.getStorageMaxSize());
            prepareStatement.setLong(4, storage.getId());

            int response = prepareStatement.executeUpdate();

            System.out.println("Update was finished with result " + response);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return storage;
    }

    public void delete(long id) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID FROM STORAGES");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM STORAGES WHERE ID = ?");

            preparedStatement.setLong(1, id);
            int response = 0;

            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    response = preparedStatement.executeUpdate();
                }
            }
            System.out.println("Delete was finished with result " + response);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public Storage findById(long id) {
        if (id == 0)
            return null;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STORAGES");

            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    return new Storage(resultSet.getLong(1), Storage.stringToFormatsArray(resultSet.getString(2)), resultSet.getString(3), resultSet.getLong(4));
                }
            }
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

