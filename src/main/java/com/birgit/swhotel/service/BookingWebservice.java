
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.HotelRepo;
import com.birgit.swhotel.repo.RoomRepo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.transaction.Transactional;

@WebService
@SessionScoped
public class BookingWebservice implements Serializable 
{
    @Inject
    private HotelRepo hotelRepo;
    
    @Inject
    private RoomRepo roomRepo;
    
    @Inject
    private BookingService bookingService;
    
    @Inject
    private UserService userService;
    
    @Inject
    private Logger logger;
    
    @WebMethod
    @Transactional
    public List<Room> getHotelRooms(String str, Date date, int nights)
    {
        List<Hotel> hotels = hotelRepo.getAll();
        if(hotels == null)
            return null;
        List<Room> rooms = new ArrayList<>();
        for(int i=0; i<hotels.size(); i++)
        {
            List<Room> hotelRooms = bookingService.getAvailableRooms(hotels.get(i), date, nights);
            if(hotelRooms != null)
            {
                for(Room room: hotelRooms)
                    rooms.add(room);
            }
        }   
        return rooms;
    }
    
    @WebMethod
    public Booking bookRoom(Booking booking, User user)
    {
        System.out.println("book room");
        User u = userService.registerUser(user);
        if(u == null)
        {
            System.out.println("login fail");
            return null;
        }
        booking.setUser(u);
        
        try{
            Room r = (Room) roomRepo.merge(booking.getRoom());
            booking.setRoom(r);
            booking = bookingService.makeBooking(booking, null, null);
            logger.info("remote booking successful");
        }
        catch(Exception e)
        {
            logger.info("remote booking error");
            return null;
        }
        finally{
        userService.logout();
        }
        return booking;
    }
    
}
