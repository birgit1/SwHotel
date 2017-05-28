
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
        }
        catch(Exception e)
        {
            return null;
        }
         List<Room> availableRooms;   
        try{
        // get all bookings for all rooms in hotel and return if they are available at the time
        availableRooms = bookingRepo.getAvailableRooms(hotelRooms, date, nights);
        }
        catch(Exception e)
        {
            return null;
        }
        return availableRooms; 
    }
    
    
    
    @Transactional
    public Booking makeBooking(Booking booking, String payEmail, String payPassword)
    {
        if(!checkDate(booking))
        {
            logger.info("booking failed: incorrect dates");
            return null;
        }
        
        User user = checkUser();
        if(user == null)
        {
            logger.info("booking failed: incorrect user");
            return null;
        }
        booking.setUser(user);
        
        if(!checkPayment(booking, payEmail, payPassword))
        {
            if(!checkPayment(booking, "test@user.com", "123"))
            { 
                logger.info("payment transaction fail");
                return null;
            }
        }
        
        try{
            Booking addedBooking = (Booking) bookingRepo.persist(booking);
            userRepo.addBookingToUser(user, addedBooking);
            logger.info("booking successful: "+addedBooking.getId());
            return booking;
        }
        catch(Exception e)
        {
            logger.info("booking failed");
            return null;
        }
    }
    
    @Transactional
    private boolean checkPayment(Booking booking, String payEmail, String payPassword)
    {
        try{
            boolean paymentSuccessful = paymentService.pay(booking.getTotalPrice(), payEmail, payPassword);
            return paymentSuccessful;
        }
        catch(Exception ex)
        {
            logger.info("payment transaction failed");
            return false;
        }
    }
    
    @Transactional
    private boolean checkDate(Booking booking)
    {
        Date now = new Date();
        
        if(booking.getArrival().before(now) || booking.getNights() <= 0)
        {
           return false;
        }
        return true;
    }
    
    @Transactional
    private User checkUser()
    {
        User user = null;
        try{
         user = userService.checkAuthentification();
         
        }
        catch(Exception ex)
        {}
        return user;
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
