
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
    //@Inject
    //private Logger logger;
    
    @Inject 
    private UserRepo userRepo;
    
    
    public User login(String email, String password)
    {
        User user = userRepo.authenticateUser(email, password);
        if(user == null)
        {
            //logger.info("user authentication failed");
            System.out.println("user authentification failed");
            return null;
        }
        loggedInUser = user;
        
        System.out.println("userAuthentification successful: "+loggedInUser.toString());
        return user;
    }
    
    @Transactional
    public User registerUser(User user)
    {
        //if(userRepo.getUserByEmail(user.getEmail()) == null)
        
            User u = (User) userRepo.persist(user);
            loggedInUser = u;
            System.out.println("user registered successfully");
            System.out.println(u.toString());
            return u;
        
        /*System.out.println("registration user already exists");
        return null;*/
    }
    
    @Transactional
    public User checkAuthentification()
    {
        System.out.println("authenticate user");
        if(loggedInUser== null)
            System.out.println("no logged in user");
        else
            System.out.println("session User: "+loggedInUser.toString());
        return loggedInUser;
    }
    
    public void logout()
    {
        loggedInUser = null;
    }
    
    private User loggedInUser = null;
    
}
