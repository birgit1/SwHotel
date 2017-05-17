
package com.birgit.swhotel.service;

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
import javax.transaction.Transactional;

@RequestScoped
public class BookingService 
{
    @Inject
    private Logger logger;
    
    @Inject
    private RoomRepo roomRepo;
    
    @Inject
    private BookingRepo bookingRepo;
    
    @Inject
    private UserRepo userRepo;
  
    
    @Transactional
    public List<Room> getAvailableRooms(Hotel hotel, Date date, int nights)
    {
        // find all rooms in certain hotel
        List<Room> hotelRooms;
        logger.info("########### BookingService: check availalbility");
        // get all rooms of hotel
        try{
        hotelRooms = roomRepo.getHotelRooms(hotel);
        logger.info("# hotel rooms found: "+hotelRooms.size());
        }
        catch(Exception e)
        {
            logger.info("FAIL finding hotel rooms 1");
            e.printStackTrace();
            return null;
        }
        
        try{
            
        // get all bookings for all rooms in hotel and return if they are available at the time
        List<Room> availableRooms = bookingRepo.getAvailableRooms(hotelRooms, date, nights);
        logger.info("# available rooms found: "+availableRooms.size());
        return availableRooms;
        }
        catch(Exception e)
        {
            logger.info("FAIL finding available rooms 2");
            e.printStackTrace();
        }
        return null;
        
    }
    
    @Transactional
    public Booking makeBooking(Booking booking, User user)
    {
        Booking b = bookingRepo.addBooking(booking);
        userRepo.addBookingToUser(user, booking);
        logger.info("booking successful: "+b.getId());
        return booking;
    }
    
    @Transactional
    public Booking removeBooking(Booking booking)
    {
        User user = booking.getUser();
        userRepo.removeBooking(user, booking);
         Booking b = bookingRepo.deleteBooking(booking);
         logger.info("booking deleted: "+b.getId());
       return booking;
    }
    
    
    
}
