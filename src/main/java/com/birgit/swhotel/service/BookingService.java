
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
     RoomRepo roomRepo;
    
    @Inject
     BookingRepo bookingRepo;
    
    @Inject
     UserRepo userRepo;
  
    
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
    public Booking makeBooking(Booking booking)
    {
        bookingRepo.persist(booking);
        User user = booking.getUser();
        userRepo.addBookingToUser(user, booking);
        logger.info("booking successful: ");
        return booking;
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
