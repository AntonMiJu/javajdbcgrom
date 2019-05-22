package hibernate.lesson4.demo;


import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.service.HotelService;

public class HotelDemo {
    public static void main(String[] args) throws Exception{
        HotelController hotelController = new HotelController();
        HotelService hotelService = new HotelService();
        System.out.println(hotelService.findByName("Hilton"));
    }
}
