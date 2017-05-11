
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class HotelService 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    // Schreibzugriff
    @Transactional
    public Hotel addHotel(Hotel hotel)
    {
        entityManager.persist(hotel);
        return hotel;
    }
    
    // Lesezugriff
    public List<Hotel> getAllHotels()
    {
        TypedQuery<Hotel> query = entityManager.createQuery("SELECT h FROM Hotel AS h", Hotel.class);
        List<Hotel> result = query.getResultList();
        return result;
    }
    
    public Hotel getHotelById(int id)
    {
        System.out.println("get hotel: "+id);
        Hotel hotel = entityManager.find(Hotel.class, id);
        return hotel;
    }
}
