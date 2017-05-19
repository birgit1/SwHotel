
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.service.UserService;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BookingModel 
{
    @Inject
    private UserService userService;
    
    private User user;
    
    @PostConstruct
    public void init() 
    {
        user = userService.checkAuthentification();
        // no user logged in
        if(user == null)
        {
            
        }
    }
    
}
