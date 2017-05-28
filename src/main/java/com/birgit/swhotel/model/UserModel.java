
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.UserRepo;
import com.birgit.swhotel.service.BookingService;
import com.birgit.swhotel.service.UserService;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserModel implements Serializable
{
    
    @Inject 
    private UserService userService;
    
    @Inject
    private UserRepo userRepo;
    
    
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
    
}
