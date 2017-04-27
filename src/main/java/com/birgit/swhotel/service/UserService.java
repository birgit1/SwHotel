package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
    
    // Lesezugriff
    public List<User> getAllUsers()
    {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User AS u", User.class);
        List<User> result = query.getResultList();
        return result;
    }
}
