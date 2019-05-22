package hibernate.lesson4.controller;

import hibernate.lesson4.objects.Session;
import hibernate.lesson4.objects.UserType;
import hibernate.lesson4.service.OrderService;

import java.util.Date;

public class OrderController {
    private Session session = new Session();
    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {
        validateLogin();
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        validateLogin();
        orderService.cancelReservation(roomId, userId);
    }

    private void validateLogin() throws Exception {
        if (session.getCurrentUser() == null)
            throw new Exception("You must be logined.");
    }

    private void validateLoginAdmin() throws Exception {
        if (session.getCurrentUser() == null)
            throw new Exception("You must be logined.");
        if (session.getCurrentUser().getUserType().equals(UserType.USER))
            throw new Exception("You must have admin rights");
    }
}
