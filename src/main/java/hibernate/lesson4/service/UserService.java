package hibernate.lesson4.service;

import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.objects.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User registerUser(User user) throws Exception {
        if (userDAO.findByName(user.getUserName()) != null || userDAO.findById(user.getId()) != null)
            throw new Exception("User " + user.getId() + " already in system");
        return userDAO.save(user);
    }

    public User findByName(String name) {
        return userDAO.findByName(name);
    }
}
