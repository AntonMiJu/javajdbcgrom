package hibernate.lesson4.controller;

import hibernate.lesson4.objects.Hotel;
import hibernate.lesson4.objects.Session;
import hibernate.lesson4.objects.UserType;
import hibernate.lesson4.service.HotelService;

public class HotelController {
    private Session session = new Session();
    private HotelService hotelService = new HotelService();

    public Hotel addHotel(Hotel hotel) throws Exception{
        validateLoginAdmin();
        return hotelService.addHotel(hotel);
    }

    public void deleteHotel(long id) throws Exception{
        validateLoginAdmin();
        hotelService.deleteHotel(id);
    }

    public Hotel findHotelByName(String name) throws Exception{
        validateLogin();
        return hotelService.findByName(name);
    }

    public Hotel findHotelByCity(String city) throws Exception{
        validateLogin();
        return hotelService.findByCity(city);
    }

    private void validateLogin() throws Exception{
        if (session.getCurrentUser() == null)
            throw new Exception("You must be logined.");
    }

    private void validateLoginAdmin() throws Exception{
        if (session.getCurrentUser() == null)
            throw new Exception("You must be logined.");
        if (session.getCurrentUser().getUserType().equals(UserType.USER))
            throw new Exception("You must have admin rights");
    }
}
