package jdbc.lesson4.homework;

import java.sql.*;
import java.util.List;

public class Controller {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");

    private FileDAO fileDAO = new FileDAO();
    private StorageDAO storageDAO = new StorageDAO();

    public void put(Storage storage, File file) {
        try (Connection connection = getConnection()) {
            putFile(storage, file, connection);
        } catch (SQLException e) {
            System.err.println("Putting file " + file.getId() + " to storage " + storage.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    public void putAll(Storage storage, List<File> files) {
        try (Connection connection = getConnection()) {
            putListOfFiles(storage, files, connection);
        } catch (SQLException e) {
            System.err.println("Putting list of files to storage " + storage.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    public void delete(Storage storage, File file) {
        try (Connection connection = getConnection()) {
            deleteFile(storage, file, connection);
        } catch (SQLException e) {
            System.err.println("Deleting file " + file.getId() + " from storage " + storage.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) {
        try (Connection connection = getConnection()) {
            transferAllFiles(storageFrom, storageTo, connection);
        } catch (SQLException e) {
            System.err.println("Transferring files from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) {
        try (Connection connection = getConnection()) {
            transferFileById(storageFrom, storageTo, id, connection);
        } catch (SQLException e) {
            System.err.println("Transferring file " + id + " from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    private void putFile(Storage storage, File file, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            fileInDB(file);
            if (validateFile(storage, file)) {
                file.setStorage(storage);
                fileDAO.update(file);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    private void putListOfFiles(Storage storage, List<File> files, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            for (File file : files) {
                fileInDB(file);
                if (validateFile(storage, file)) {
                    file.setStorage(storage);
                    fileDAO.update(file);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    private void deleteFile(Storage storage, File file, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            fileInDB(file);
            file.setStorage(storage);
            fileDAO.update(file);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    private void transferAllFiles(Storage storageFrom, Storage storageTo, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            for (File el : fileDAO.findFileByStorage(storageFrom))
                putFile(storageTo,el,connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    private void transferFileById(Storage storageFrom, Storage storageTo, long id, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            File file = fileDAO.findById(id);
            fileInDB(file);
            if (validateFile(storageTo, file)) {
                file.setStorage(storageTo);
                fileDAO.update(file);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    private void fileInDB(File file) throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID,NAME_F FROM FILES");
            while (resultSet.next()) {
                if (resultSet.getLong(1) == file.getId() && resultSet.getString(2).equals(file.getName()))
                    return;
            }
            throw new SQLException();
        } catch (SQLException e) {
            System.err.println("File " + file.getId() + " is not in DB");
            throw e;
        }
    }

    private boolean validateFile(Storage storage, File file) {
        if (isFormatSupported(storage, file) && file.getSize() < storage.getStorageMaxSize())
            return true;
        return false;
    }

    private boolean isFormatSupported(Storage storage, File file) {
        for (String el : storage.getFormatsSupported()) {
            if (el.equals(file.getFormat()))
                return true;
        }
        return false;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
