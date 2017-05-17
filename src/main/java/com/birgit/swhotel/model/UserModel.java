
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.UserRepo;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UserModel implements Serializable
{
    
    @Inject 
    private UserRepo userService;
    
    private User loggedInUser = null;
    private String email, password, name;
    private String message = null;
    
    
    public void registerUser()
    {
        User user = new User();
        if(name != null && email != null && password != null)
        {
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            userService.createUser(user);
            authenticateUser();
        }
        
    }
    
    public void authenticateUser()
    {
        loggedInUser = userService.authenticateUser(email, password);
        if(loggedInUser == null)
        {
            message = "authentification fail; wrong password or email";
        }
        else
        {
            message = "authentificated";
        }
        email = null;
        password = null;
        name = null;
    }
    
    // getter & setter *********************************************

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

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