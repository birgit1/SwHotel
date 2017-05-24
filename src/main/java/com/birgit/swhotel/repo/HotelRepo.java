
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.utils.LoggerProvider;
import java.io.Serializable;
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
public class HotelRepo extends SingleIdEntityRepository implements Serializable
{
    //@PersistenceContext(unitName="SwHotelPU")
    //private EntityManager entityManager;
    
    @Inject
    Logger logger;
    
    @Inject
    private RoomRepo roomRepo;
    
    @Inject
    private BookingRepo bookingRepo;
    
    @Inject
    private UserRepo userRepo;

    public HotelRepo()
    {
        super(Hotel.class);
    }
    
   
    
    
    public List<Hotel> findHotelByString(String s)
    {
        // first the bookings for the hotel should be deleted
        
        TypedQuery query = this.getEntityManager().createQuery("SELECT h FROM Hotel h WHERE h.name like :s OR h.address.city like :s OR h.address.country like :s", Hotel.class);
        query.setParameter("s", s);
        List<Hotel> hotels = query.getResultList();
        if(hotels == null)
            logger.info("no hotel found");
        else
            logger.info(hotels.get(0).toString());
        return hotels;
    }
    
    
   
}
