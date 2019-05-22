package hibernate.lesson4.dao;

import hibernate.lesson4.objects.Order;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.util.List;

public class OrderDAO extends GeneralDAO<Order>{
    public OrderDAO() {
        setParameterOfClass(Order.class);
        setDelete(delete);
        setGetAll(getAll);
    }

    private static final String delete = "DELETE FROM ORDERS WHERE ID = ?";
    private static final String getAll = "SELECT * FROM ORDERS";

    @Override
    public Order save(Order order) {
        return super.save(order);
    }

    @Override
    public Order update(Order order) {
        return super.update(order);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public Order findById(long id) {
        return super.findById(id);
    }

    @Override
    public List<Order> getAll() {
        return super.getAll();
    }
}
