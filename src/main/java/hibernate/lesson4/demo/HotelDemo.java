package hibernate.lesson4.demo;

import hibernate.lesson4.dao.HotelDAO;

public class HotelDemo {
    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
        System.out.println(hotelDAO.getAll());
    }
}
