
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.UserRepo;
import com.birgit.swhotel.service.BookingService;
import com.birgit.swhotel.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BookingModel 
{
    
    @Inject
    private UserService userService;
    
    @Inject
    private BookingService bookingService;
    
    @Inject
    private BookingRepo bookingRepo;
    
    
    //private String userName;
    private List<Booking> userBookings = new ArrayList<>();
   
    public String checkLogin()
    {
        if(userService.checkAuthentification()== null)
            return "login";
        else
            return "bookings";
    }
    
    
    
    public void deleteBooking(Booking booking)
    {
        bookingService.removeBooking(booking);
        userBookings.remove(booking);
    }
    

    public List<Booking> getUserBookings() 
    {
        User u = userService.checkAuthentification();
        userBookings = bookingRepo.getBookingsByUser(u);
          
        return userBookings;
    }
    
    public boolean userHasBookings()
    {
        if(userBookings == null || userBookings.size() <= 0)
        {
            return false;
        }
        return true;            
    }
    public boolean userHasNoBookings()
    {
        return (!userHasBookings());
    }

    public void setUserBookings(List<Booking> userBookings) {
        this.userBookings = userBookings;
    }

    
    
}   
