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
        try {
            fileInDB(file);
            if (validateFile(storage, file)) {
                file.setStorage(storage);
                fileDAO.update(file);
            }
        } catch (SQLException e){
            System.err.println("Putting file to storage " + storage.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    public void putAll(Storage storage, List<File> files) {
        try {
            for (File file : files) {
                fileInDB(file);
                if (validateFile(storage, file)) {
                    file.setStorage(storage);
                    fileDAO.update(file);
                }
            }
        } catch (SQLException e) {
            System.err.println("Putting list of files to storage " + storage.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    public void delete(Storage storage, File file) {
        try {
            fileInDB(file);
            file.setStorage(null);
            fileDAO.update(file);
        } catch (SQLException e) {
            System.err.println("Deleting file " + file.getId() + " from storage " + storage.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) {
        for (File el : fileDAO.findFileByStorage(storageFrom))
            put(storageTo,el);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) {
        try {
            File file = fileDAO.findById(id);
            fileInDB(file);
            if (validateFile(storageTo, file)) {
                file.setStorage(storageTo);
                fileDAO.update(file);
            }
        } catch (SQLException e) {
            System.err.println("Transferring file " + id + " from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + " went wrong");
            e.printStackTrace();
        }
    }

    private void fileInDB(File file) throws SQLException {
        try {
            File foundedFile = fileDAO.findById(file.getId());
            if (file.getId() == foundedFile.getId() && file.getName().equals(foundedFile.getName()))
                return;
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
}
