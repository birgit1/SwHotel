
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.UserRepo;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class UserService implements Serializable 
{
    @Inject
    private Logger logger;
    
    @Inject 
    private UserRepo userRepo;
    
    
    public User login(String email, String password)
    {
        User user = userRepo.authenticateUser(email, password);
        if(user == null)
        {
            logger.info("user authentication failed");
            return null;
        }
        loggedInUser = user;
        return user;
    }
    
    public User registerUser(User user)
    {
        if(userRepo.getUserByEmail(user.getEmail()) == null)
        {
            User u = (User) userRepo.persist(user);
            loggedInUser = u;
            return u;
        }
        return null;
    }
    
    public User checkAuthentification()
    {
        return loggedInUser;
    }
    
    public void logout()
    {
        loggedInUser = null;
    }
    
    private User loggedInUser = null;
    
}
