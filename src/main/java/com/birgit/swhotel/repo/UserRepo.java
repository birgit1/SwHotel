package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.User;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@SessionScoped
public class UserRepo extends SingleIdEntityRepository implements Serializable 
{
    public UserRepo()
    {
        super(User.class);
    }
    
    
    @Transactional
    public void addBookingToUser(User user, Booking booking)
    {
        user = (User) merge(user);
        user.setBooking(booking);
        persist(user);
    }
    
    @Transactional
    public void removeBooking(User user, Booking booking)
    {
        user = (User) merge(user);
        user.removeBooking(booking);
        persist(user);
    }
    
    @Transactional
    public List<Booking> getUserBookings(User user)
    {
        User u = (User) getById(user.getId());
        List<Booking> bookings = u.getBookings();
        return bookings;
    }
    
    @Transactional
    public User authenticateUser(String email, String password)
    {
        TypedQuery<User> query = this.getEntityManager().createQuery(
            "SELECT u FROM User u WHERE u.email = "
                    + ":parameter1 AND u.password = :parameter2", User.class);
        query.setParameter("parameter1", email);
        query.setParameter("parameter2", password);
        List<User> users = query.getResultList();
        if(users.isEmpty()) 
        {
            return null;
        } 
        else 
        {
            User user = users.get(0);
            return user;
        }  
    }
    
    @Transactional
    public User getUserByEmail(String email)
    {
        TypedQuery<User> query = this.getEntityManager().createQuery(
            "SELECT u FROM User u WHERE u.email = "
                    + ":parameter1", User.class);
        query.setParameter("parameter1", email);
        User user = query.getSingleResult();
        
        return user;
    }
}
