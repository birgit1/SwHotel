
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.UserRepo;
import com.birgit.swhotel.service.BookingService;
import com.birgit.swhotel.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BookingModel 
{
    private final String TAG = "BookingModel";
    
    @Inject
    private UserService userService;
    
    @Inject
    private BookingService bookingService;
    
    @Inject
    private BookingRepo bookingRepo;
    
    @Inject
    private UserRepo userRepo;
    
    //private String userName;
    private List<Booking> userBookings = new ArrayList<>();
   
    public String checkLogin()
    {
        if(userService.checkAuthentification()== null)
            return "login";
        else
            return "bookings";
    }
    
    
    
    /*
    public String getBookingsForUser()
    {
        User u = userService.checkAuthentification();
        if(u != null)
        {
            System.out.println("session user: "+u.toString());
            userBookings = userRepo.getUserBookings(u);
            return "bookings";
        }
        else
        {
            System.out.println("NO session user: login first");
            return "login";
        }
    }*/
    
    
    
    public void deleteBooking(Booking booking)
    {
        bookingService.removeBooking(booking);
        userBookings.remove(booking);
    }
    

    public List<Booking> getUserBookings() 
    {
        User u = userService.checkAuthentification();
        
        System.out.println(TAG+"get bookings for session user: "+u.toString());
            
        userBookings = bookingRepo.getBookingsByUser(u);
        for(Booking b: userBookings)
            System.out.println(TAG+"booking: "+b.toString());
        System.out.println("#bookings for user: "+userBookings.size());
        
        return userBookings;
    }
    
    public boolean userHasBookings()
    {
        if(userBookings == null || userBookings.size() <= 0)
        {
            System.out.println("no user bookings");
            return false;
        }
        System.out.println(" user bookings");
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
