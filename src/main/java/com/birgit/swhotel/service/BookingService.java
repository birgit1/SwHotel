
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.RoomRepo;
import java.util.Date;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class BookingService 
{
    @Inject
    Logger logger;
    
    @Inject
    private RoomRepo roomRepo;
    
    @Inject
    private BookingRepo bookingRepo;
    
    @Inject
    private UserRepo userRepo;
  
    
    @Transactional
    public List<Room> checkAvailibility(Hotel hotel, Date date, int nights)
    {
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
            
        List<Room> availableRooms = bookingRepo.getBookingsForRooms(hotelRooms, date, nights);
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
    public Booking makeBooking(Room room, User userData, Date arrival, int nights)
    {
        User user = userRepo.authenticateUser(userData.getEmail(), userData.getPassword());
        if(user == null)   
        {
            logger.info("invalid user");
            return null;
        }
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setUser(user);
        booking.setArrival(arrival);
        booking.setNights(nights);
        Booking b = bookingRepo.addBooking(booking);
        userRepo.addBookingToUser(user, booking);
        logger.info("booking successful: "+b.getId());
        return booking;
    }
    
    
    
}
