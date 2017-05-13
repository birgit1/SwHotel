package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@RequestScoped
public class UserService 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    // Schreibzugriff
    @Transactional
    public User createUser(User user)
    {
        entityManager.persist(user);
        return user;
    }
    
    /*@Transactional
    public void testDelete()
    {
        System.out.println("TEST DELETE *****************************");
        //Query query = entityManager.createQuery("UPDATE Room r SET r.price=5");
        Query query = entityManager.createQuery("DELETE r FROM Room WHERE r.id=1401");
        
   
        int amount = query.executeUpdate();
        System.out.println("TEST DELETED ***************************** -> "+amount);       
    }*/
    
    @Transactional
    public int testDelete()
    {
        int id = 1001;
        User user = getUserById(id);
        System.out.println("TEST DELETE *****************************");
        user = entityManager.merge(user);
        entityManager.remove(user);
        System.out.println("TEST DELETED ***************************** -> ");       
        return id;
    }
    
    @Transactional
    public User deleteUser(User user)
    {
        System.out.println("userservice: delete user #"+user.getId());
        //entityManager.remove(user);
        /*Query query = entityManager.createQuery("DELETE User u FROM TUser"
                + "WHERE id="+user.getId());
        int amount = query.executeUpdate();*/
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
    
    public User getUserById(int id)
    {
        User user = entityManager.find(User.class, id);
        return user;
    }
}
