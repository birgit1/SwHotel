
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.UserRepo;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
    
    @Transactional
    public User registerUser(User user)
    { 
        if(user.getName()!= null && user.getEmail() != null && user.getPassword()!=null)
        if(!user.getName().equals("") && !user.getEmail().equals(""))
        {
            User u = (User) userRepo.persist(user);
            loggedInUser = u;
            logger.info("user registration succesfully "+u.getId());
            return u;
        }
        
        logger.info("registration failed");
        return null;
    }
    
    @Transactional
    public User checkAuthentification()
    {
        if(loggedInUser== null)
            return null;
         return loggedInUser;
    }
    
    public void logout()
    {
        loggedInUser = null;
    }
    
    private User loggedInUser = null;
    
}
