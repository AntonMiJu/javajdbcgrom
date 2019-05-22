package hibernate.lesson4.controller;

import hibernate.lesson4.objects.Session;
import hibernate.lesson4.objects.User;
import hibernate.lesson4.service.UserService;

public class UserController {
    private Session session = new Session();
    private UserService userService = new UserService();

    public User registerUser(User user) throws Exception{
        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {
        if (session.getCurrentUser() != null)
            throw new Exception("Anybody is already signed");
        User user = userService.findByName(userName);
        if (user != null && user.getPassword().equals(password))
            session.setCurrentUser(user);
        else
            throw new Exception("Login failed");
    }

    public void logout() {
        session.setCurrentUser(null);
    }
}
