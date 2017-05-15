package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.utils.LoggerProvider;
import java.io.Serializable;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@SessionScoped
public class UserService implements Serializable 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    /*@Inject
    LoggerFactory logger;*/
    
    // Schreibzugriff
    @Transactional
    public User createUser(User user)
    {
        entityManager.persist(user);
        return user;
    }
    
    
    @Transactional
    public User deleteUser(User user)
    {
        System.out.println("userservice: delete user #"+user.getId());
        user = entityManager.merge(user);
        entityManager.remove(user);
        return user;
    }
    
    // Lesezugriff
    public List<User> getAllUsers()
    {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User AS u", User.class);
        List<User> result = query.getResultList();
        return result;
    }
    
    public User getUserById(long id)
    {
        User user = entityManager.find(User.class, id);
        return user;
    }
    
    public User authenticateUser(String email, String password)
    {
        TypedQuery<User> query = entityManager.createQuery(
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
    
    
    
}
