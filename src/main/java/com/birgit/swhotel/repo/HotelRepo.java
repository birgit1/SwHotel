
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.utils.LoggerProvider;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class HotelRepo
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    @Inject
    Logger logger;
    
    // Schreibzugriff
    @Transactional
    public Hotel addHotel(Hotel hotel)
    {
        logger.log(Level.INFO, "HOTEL SERVICE: add hotel {0}", hotel.getId());
        entityManager.persist(hotel);
        return hotel;
    }
    
    @Transactional
    public Hotel deleteHotel(Hotel hotel)
    {
        logger.log(Level.INFO, "HOTEL SERVICE: delete hotel {0}", hotel.getId());
        hotel = entityManager.merge(hotel);
        entityManager.remove(hotel);
        return hotel;
    }
    
    // Lesezugriff
    public List<Hotel> getAllHotels()
    {
        TypedQuery<Hotel> query = entityManager.createQuery("SELECT h FROM Hotel AS h", Hotel.class);
        List<Hotel> result = query.getResultList();
        logger.log(Level.INFO, "HOTEL SERVICE: get all hotels {0}", result.size());
        return result;
    }
    
    public Hotel getHotelById(long id)
    {
        Hotel hotel = entityManager.find(Hotel.class, id);
        return hotel;
    }
    
    public List<Hotel> findHotelByString(String s)
    {
        String queryString = "SELECT h FROM Hotel h WHERE h.address.city = :s OR h.address.country = :s";
        TypedQuery query = entityManager.createQuery(queryString, Hotel.class);
        query.setParameter("s", s);
        List<Hotel> hotels = query.getResultList();
        logger.log(Level.INFO, "HOTEL SERVICE: get all hotels {0}", hotels.size());
        return hotels;
    }
    
    
   
}
