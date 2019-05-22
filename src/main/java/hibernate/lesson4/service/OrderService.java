package hibernate.lesson4.service;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.objects.Order;
import hibernate.lesson4.objects.Room;

import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {
        Order order = new Order();
        Room room = roomDAO.findById(roomId);
        if (room.getDateAvailableFrom().after(dateFrom))
            throw new Exception("DateFrom "+ dateFrom +" must be after available date of room.");

        order.setUserOrdered(userDAO.findById(userId));
        order.setRoom(roomDAO.findById(roomId));
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setMoneyPaid(room.getPrice());

        room.setDateAvailableFrom(dateTo);

        orderDAO.save(order);
        roomDAO.update(room);
    }

    public void cancelReservation(long roomId, long userId) {
        Order order = new Order();
        Room room = new Room();
        for (Order el : orderDAO.getAll()){
            if (el.getUserOrdered().getId() == userId && el.getRoom().getId() == roomId)
                order = el;
        }

        room.setDateAvailableFrom(order.getDateFrom());

        orderDAO.delete(order.getId());
        roomDAO.update(room);
    }
}
