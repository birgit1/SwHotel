
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Hotel;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

@RequestScoped
public class HotelRepo extends SingleIdEntityRepository implements Serializable
{
    @Inject
    Logger logger;
    

    public HotelRepo()
    {
        super(Hotel.class);
    }
   
    
    public List<Hotel> findHotelByString(String s)
    {
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
