package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.User;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@SessionScoped
public class UserRepo extends SingleIdEntityRepository implements Serializable 
{
    private final String TAG = "UserRepo";
    //@Inject
    //Logger logger;

    public UserRepo()
    {
        super(User.class);
    }
    
    
    @Transactional
    public void addBookingToUser(User user, Booking booking)
    {
        //user = entityManager.merge(user);
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
        System.out.println(TAG+" -> get bookings for user"+user.getId());
        //user = (User) merge(user);
        User u = (User) getById(user.getId());
        List<Booking> bookings = u.getBookings();
        System.out.println(TAG+" -> bookings found: "+bookings.size());
        
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
        System.out.println("get user by email: "+email);
        TypedQuery<User> query = this.getEntityManager().createQuery(
            "SELECT u FROM User u WHERE u.email = "
                    + ":parameter1", User.class);
        query.setParameter("parameter1", email);
        User user = query.getSingleResult();
        if(user == null)
            System.out.println("no such user");
        else
            System.out.println("user already registered");
        return user;
    }
}
