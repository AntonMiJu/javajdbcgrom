package hibernate.lesson4.service;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.objects.Hotel;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();

    public Hotel addHotel(Hotel hotel) throws Exception{
        if (hotelDAO.findById(hotel.getId())!= null)
            throw new Exception("Hotel "+ hotel.getId()+" already in DB.");
        return hotelDAO.save(hotel);
    }

    public void deleteHotel(long id){
        hotelDAO.delete(id);
    }

    public Hotel findByName(String name){
        return hotelDAO.findByName(name);
    }

    public Hotel findByCity(String city){
        return hotelDAO.findByCity(city);
    }
}
