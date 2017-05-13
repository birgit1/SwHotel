
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
    
    @Transactional
    public Hotel deleteHotel(Hotel hotel)
    {
        hotel = entityManager.merge(hotel);
        entityManager.remove(hotel);
        return hotel;
    }
    
    // Lesezugriff
    public List<Hotel> getAllHotels()
    {
        TypedQuery<Hotel> query = entityManager.createQuery("SELECT h FROM Hotel AS h", Hotel.class);
        List<Hotel> result = query.getResultList();
        System.out.println("hotels retrieved: "+result.size());
        return result;
    }
    
    public Hotel getHotelById(int id)
    {
        System.out.println("get hotel: "+id);
        Hotel hotel = entityManager.find(Hotel.class, id);
        return hotel;
    }
    
    public List<Hotel> findHotelByString(String s)
    {
        String queryString = "SELECT h FROM Hotel h WHERE h.address.city = :s OR h.address.country = :s";
        TypedQuery query = entityManager.createQuery(queryString, Hotel.class);
        query.setParameter("s", s);
        List<Hotel> hotels = query.getResultList();
        System.out.println("#hotels: "+hotels.size());
        return hotels;
    }
    
   
}
