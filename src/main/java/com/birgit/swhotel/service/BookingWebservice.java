
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.HotelRepo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.transaction.Transactional;

@WebService
@RequestScoped
public class BookingWebservice  
{
    @Inject
    private HotelRepo hotelRepo;
    
    @Inject
    private BookingService bookingService;
    
    @Inject
    private UserService userService;
    
    @WebMethod
    @Transactional
    public List<Room> getHotelRooms(String str, Date date, int nights)
    {
        List<Hotel> hotels = hotelRepo.getAll();
        if(hotels == null)
            return null;
        this.arrivalDate = date;
        this.nights = nights;
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
    @Transactional
    public Booking bookRoom(Booking booking, User user)
    {
        System.out.println("book room");
        User u = userService.registerUser(user);
        if(u == null)
        {
            System.out.println("login fail");
            return null;
        }
        return booking;
    }
    
    /*@WebMethod
    public void bookRoom( Room room, User user)
    {
        System.out.println("book room");
        User u = userService.registerUser(user);
        if(u == null)
        {
            System.out.println("login fail");
            return;// null;
        }
        /*else
        {
        
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setUser(u);
        booking.setArrival(arrivalDate);
        booking.setNights(nights);
        System.out.println(booking.toString());
        
        try{
        booking = bookingService.makeBooking(booking);
        }
        catch(Exception e)
        {
            System.out.println("booking could not be performed");
            return ;//null;
        }
        finally{
        arrivalDate=null;
        nights=0;
        userService.logout();
        }
        System.out.println("finished remote booking");
        //return booking;
        }*
        
    }*/
    
    private Date arrivalDate;
    private int nights;
    
}
