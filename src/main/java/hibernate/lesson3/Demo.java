package hibernate.lesson3;

import hibernate.lesson3.dao.HotelDAO;
import hibernate.lesson3.dao.RoomDAO;
import hibernate.lesson3.objects.Hotel;
import hibernate.lesson3.objects.Room;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        Hotel hotel = new Hotel(1,"Hilton","Ua","Kyiv","Hreshchatyk");

        hotelDAO.save(new Hotel(1,"Hilton","Ua","Kyiv","Hreshchatyk"));
        roomDAO.save(new Room(1,2,1200,1,0,new Date(),hotel));
        roomDAO.save(new Room(2,4,5200,0,0,new Date(),hotel));
    }
}
