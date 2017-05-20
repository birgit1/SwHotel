
package com.birgit.swhotel.service;

import com.birgit.swhotel.service.payment.AbstractPaymentService;
import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.RoomRepo;
import com.birgit.swhotel.repo.UserRepo;
import java.util.Date;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;

//@WebService
@RequestScoped
public class BookingService 
{
    @Inject
    private Logger logger;
    
    @Inject
     RoomRepo roomRepo;
    
    @Inject
     BookingRepo bookingRepo;
    
    @Inject
     UserRepo userRepo;
    
    @Inject 
    private UserService userService;
    
    @Inject
    private AbstractPaymentService paymentService;
  
    //@WebMethod
    @Transactional
    public List<Room> getAvailableRooms(Hotel hotel, Date date, int nights)
    {
        // find all rooms in certain hotel
        List<Room> hotelRooms;
        // get all rooms of hotel
        
        try{
            
        hotelRooms = roomRepo.getHotelRooms(hotel);
        logger.info("# hotel rooms found: "+hotelRooms.size());
        }
        catch(Exception e)
        {
            logger.info("no rooms in hotel");
            return null;
        }
         List<Room> availableRooms;   
        try{
        // get all bookings for all rooms in hotel and return if they are available at the time
        availableRooms = bookingRepo.getAvailableRooms(hotelRooms, date, nights);
        logger.info("# available rooms found: "+availableRooms.size());
        }
        catch(Exception e)
        {
            logger.info("all rooms booked out");
            return null;
        }
        return availableRooms; 
    }
    
    @Transactional
    //@WebMethode
    public String makeBookingFromExtern(Room room, Date date, int nights, User user)
    {
        // check if user exists if not register
        // make the booking
        return "booking id confirmed";
    }
    
    @Transactional
    public Booking makeBooking(Booking booking)
    {
        User user;
        try{
         user = userService.checkAuthentification();
        booking.setUser(user);
        System.out.println(user.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("user authentication fail");
            return null;
        }
        try{
            if(!paymentService.pay(booking.getTotalPrice()))
                return null;
            
        }
        catch(Exception ex)
        {
            return null;
        }
        try{
        System.out.println("make booking");
        Booking addedBooking = (Booking) bookingRepo.persist(booking);
        
        userRepo.addBookingToUser(user, addedBooking);
        logger.info("booking successful: ");
        return booking;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("booking fail");
            return null;
        }
        
    }
    
    @Transactional
    public Booking removeBooking(Booking booking)
    {
        User user = booking.getUser();
        userRepo.removeBooking(user, booking);
        bookingRepo.delete(booking);
        logger.info("booking deleted: "+booking.getId());
        return booking;
    }
    
    
    
}
