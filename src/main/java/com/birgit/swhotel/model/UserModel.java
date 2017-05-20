
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.UserRepo;
import com.birgit.swhotel.service.BookingService;
import com.birgit.swhotel.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserModel implements Serializable
{
    
    @Inject 
     UserService userService;
    
    @Inject
    private UserRepo userRepo;
    
    @Inject
    private BookingService bookingService;
    
    private User loggedInUser = null;
    private String email, password, name;
    private String message = null;
    
    public String registerUser()
    {
        User user = new User();
        if(name != null && email != null && password != null)
        {
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            User u = userService.registerUser(user);
        }
        return "bookingDetail";
    }
    
    public String login()
    {
        
        if(authenticateUser()!= null)
            return "bookings";
        return null;
    }
    
    public String loginBook()
    {
        if(authenticateUser()!= null)
            return "bookingDetail";
        return null;
    }
    
    private User authenticateUser()
    {
        loggedInUser = userService.login(email, password);
        if(loggedInUser == null)
        {
            message = "authentification fail; wrong password or email";
            return null;
        }
        
            message = "authentificated";
        email = null;
        password = null;
        name = null;
        return loggedInUser;
    }
    
    public String logout()
    {
        userService.logout();
        return "hotels";
    }
    
    // getter & setter *********************************************

    


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /// bookings ****************
    private List<Booking> userBookings = new ArrayList<>();
    
    public String gotoLogin()
    {
        System.out.println("go to login");
        return "login";
    }
    
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
    }
    
    
    
    public void deleteBooking(Booking booking)
    {
        bookingService.removeBooking(booking);
        userBookings.remove(booking);
    }
    
   
    public List<Booking> getUserBookings() 
    {
        User u = userService.checkAuthentification();
        if(u == null)
        {
            System.out.println("NO session user: login first");
            login();
            return null;
        }
        System.out.println(" session user: "+u.toString());
            
        userBookings = userRepo.getUserBookings(u);
        return userBookings;
    }

    public void setUserBookings(List<Booking> userBookings) {
        this.userBookings = userBookings;
    }
    
}
